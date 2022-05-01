package com.keyworksteste.keyworks.service;

import com.keyworksteste.keyworks.exception.DuplicatedObjectException;
import com.keyworksteste.keyworks.exception.NotFoundRecordException;
import com.keyworksteste.keyworks.model.Card;
import com.keyworksteste.keyworks.model.dto.CardRequestDTO;
import com.keyworksteste.keyworks.model.dto.CardResponseDTO;
import com.keyworksteste.keyworks.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CardResponseDTO create(CardRequestDTO cardRequestDTO) {

        if(Optional.ofNullable(cardRepository.findByTitleContainingIgnoreCase(
                cardRequestDTO.getTitle())).isPresent()){
            throw new DuplicatedObjectException("422", "Duplicated object");
        }

        Card card = modelMapper.map(cardRequestDTO, Card.class);
        card.setCreatedAt(LocalDateTime.now());
        return modelMapper.map(cardRepository.save(card), CardResponseDTO.class);
    }

    public List<CardResponseDTO> getCardList(String title) {

        List<Card> cardList;

        if(title == null){
            cardList = cardRepository.findAll();
        } else {
            cardList = Collections.singletonList(cardRepository.findByTitleContainingIgnoreCase(title));
        }

        List<CardResponseDTO> cardResponseDTOList = new ArrayList<>();
        cardList.forEach(card -> {
            CardResponseDTO cardResponseDTO = modelMapper.map(card, CardResponseDTO.class);
            cardResponseDTOList.add(cardResponseDTO);
        });

        return cardResponseDTOList;
    }

    public void delete(String title) {

        Optional<Card> optionalCard = Optional.ofNullable(cardRepository.findByTitleContainingIgnoreCase(title));
        Card card = optionalCard.orElseThrow(() -> new NotFoundRecordException("404", "Not Found Record"));
        cardRepository.delete(card);
    }

    public void update(String title, CardRequestDTO cardRequestDTO) {

        Optional<Card> optionalCard = Optional.ofNullable(cardRepository.findByTitleContainingIgnoreCase(title));
        Card card = optionalCard.orElseThrow(() -> new NotFoundRecordException("404", "Not Found Record"));

        card = modelMapper.map(cardRequestDTO, Card.class);

        cardRepository.save(card);
    }
}
