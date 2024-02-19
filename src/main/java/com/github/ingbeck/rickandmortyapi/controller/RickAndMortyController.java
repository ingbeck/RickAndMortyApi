package com.github.ingbeck.rickandmortyapi.controller;

import com.github.ingbeck.rickandmortyapi.model.RickAndMortyCharacter;
import com.github.ingbeck.rickandmortyapi.model.RickAndMortyResponse;
import com.github.ingbeck.rickandmortyapi.service.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ram")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService service;

    @GetMapping
    public RickAndMortyResponse getAllChars(){
        return service.getAllChars();
    }

    @GetMapping("/{id}")
    public RickAndMortyCharacter getCharacterById(@PathVariable int id){
        return service.getCharacterById(id);
    }

}
