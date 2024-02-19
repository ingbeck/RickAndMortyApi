package com.github.ingbeck.rickandmortyapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RickAndMortyResponse {
    private List<RickAndMortyCharacter> results;
}
