package com.github.ingbeck.rickandmortyapi.service;

import com.github.ingbeck.rickandmortyapi.model.RickAndMortyCharacter;
import com.github.ingbeck.rickandmortyapi.model.RickAndMortyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {


    //Test
    private RestClient rc;

    public RickAndMortyService(@Value("${basic.url}") String baseUrl){
        this.rc = RestClient.create(baseUrl);
    }

    public RickAndMortyResponse getAllChars() {
        RickAndMortyResponse response = rc.get().uri("/character").retrieve().body(RickAndMortyResponse.class);
        return response;
    }

    public RickAndMortyCharacter getCharacterById(int id) {
        RickAndMortyCharacter response= rc.get().uri("/character/"+id).retrieve().body(RickAndMortyCharacter.class);
        return response;
    }


    //Funktioniert nicht, da nur erste Seite gelesen wird
    //Lieber mit Aufrufen aus der Doku
    public List<RickAndMortyCharacter> searchCharacterByStatus(String status) {
        RickAndMortyResponse response = rc.get().uri("/character").retrieve().body(RickAndMortyResponse.class);
        return response.getResults().stream().filter(c -> c.getStatus().equals(status)).toList();
    }
    public int numberOfAliveCharsOfSpecies(String species) {
        List<RickAndMortyCharacter> searchList = searchCharacterByStatus("Alive");
        return searchList.stream().filter(c -> c.getSpecies().equals(species)).toList().size();
    }
}