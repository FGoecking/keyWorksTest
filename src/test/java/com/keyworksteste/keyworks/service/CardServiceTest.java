package com.keyworksteste.keyworks.service;

import com.keyworksteste.keyworks.exception.DuplicatedObjectException;
import com.keyworksteste.keyworks.model.Card;
import com.keyworksteste.keyworks.model.dto.CardRequestDTO;
import com.keyworksteste.keyworks.model.dto.CardResponseDTO;
import com.keyworksteste.keyworks.repository.CardRepository;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CardRepository.class, CardService.class})
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private ModelMapper modelMapper;

    private static CardRequestDTO cardRequestDTO;
    private static CardResponseDTO cardResponseDTO;

    @BeforeClass
    public static void initializr(){

        cardRequestDTO = CardRequestDTO.builder()
                .title("Title")
                .description("lorem ipsum")
                .build();

        cardResponseDTO = CardResponseDTO.builder()
                .title("Title")
                .description("lorem ipsum")
                .createdAt(LocalDateTime.now())
                .number(1L)
                .build();
    }

    @Test
    public void createTest(){

        when(cardRepository.findByTitleContainingIgnoreCase(Mockito.anyString())).thenReturn(null);
        when(modelMapper.map(Mockito.any(CardRequestDTO.class), Mockito.any())).thenReturn(new Card());
        when(cardRepository.save(Mockito.any(Card.class))).thenReturn(new Card());
        when(modelMapper.map(Mockito.any(Card.class), Mockito.any())).thenReturn(cardResponseDTO);
        CardResponseDTO cardResponseDTOLocal = cardService.create(cardRequestDTO);
        Assert.assertNotNull(cardResponseDTOLocal);
    }

    @Test
    public void getCardListTest(){

        when(cardRepository.findByTitleContainingIgnoreCase(Mockito.anyString())).thenReturn(new Card());
        when(modelMapper.map(Mockito.any(Card.class), Mockito.any())).thenReturn(cardResponseDTO);
        List<CardResponseDTO> cardResponseDTOList = cardService.getCardList("Title");
        Assert.assertNotNull(cardResponseDTOList);
    }
}
