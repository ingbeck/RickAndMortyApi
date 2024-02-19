package com.github.ingbeck.rickandmortyapi.controller;

import com.github.ingbeck.rickandmortyapi.model.RickAndMortyCharacter;
import com.github.ingbeck.rickandmortyapi.model.RickAndMortyResponse;
import com.github.ingbeck.rickandmortyapi.service.RickAndMortyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService service;

    @GetMapping()
    public RickAndMortyResponse getAllChars(){
        return service.getAllChars();
    }

    @GetMapping("/{id}")
    public RickAndMortyCharacter getCharacterById(@PathVariable int id){
        return service.getCharacterById(id);
    }

    @GetMapping("/character")
    public List<RickAndMortyCharacter> searchCharacterByStatus(@RequestParam String status){
        return service.searchCharacterByStatus(status);
    }

   @GetMapping("/species-statistics")
    public int numberOfAliveCharsOfSpecies(@RequestParam String species){
        return service.numberOfAliveCharsOfSpecies(species);
   }

}
