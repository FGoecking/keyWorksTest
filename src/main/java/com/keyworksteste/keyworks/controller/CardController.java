package com.keyworksteste.keyworks.controller;

import com.keyworksteste.keyworks.model.dto.CardRequestDTO;
import com.keyworksteste.keyworks.model.dto.CardResponseDTO;
import com.keyworksteste.keyworks.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@Api(value = "Card")
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Create new card.")
    @PostMapping
    public ResponseEntity<CardResponseDTO> create(@Validated @RequestBody CardRequestDTO cardRequestDTO){
        return new ResponseEntity<>(cardService.create(cardRequestDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Lists all cards, if it have any param title, it will return that card.")
    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getCardList(@RequestParam(required = false) String title){

        return new ResponseEntity<>(cardService.getCardList(title), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete card")
    @DeleteMapping("/{title}")
    public ResponseEntity delete(@PathVariable String title){
        cardService.delete(title);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Update card")
    @PutMapping("/{title}")
    public ResponseEntity update(@PathVariable String title, @Validated @RequestBody CardRequestDTO cardRequestDTO){
        cardService.update(title, cardRequestDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
