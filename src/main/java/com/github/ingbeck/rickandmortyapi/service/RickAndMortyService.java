package com.github.ingbeck.rickandmortyapi.service;

import com.github.ingbeck.rickandmortyapi.model.RickAndMortyCharacter;
import com.github.ingbeck.rickandmortyapi.model.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private RestClient rc = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();

    public RickAndMortyResponse getAllChars() {
        RickAndMortyResponse response = rc.get().uri("/character").retrieve().body(RickAndMortyResponse.class);
        return response;
    }

    public RickAndMortyCharacter getCharacterById(int id) {
        RickAndMortyCharacter response= rc.get().uri("/character/"+id).retrieve().body(RickAndMortyCharacter.class);
        return response;
    }
}
