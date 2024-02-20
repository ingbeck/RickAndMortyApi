package com.github.ingbeck.rickandmortyapi.service;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RickAndMortyServiceTest {

    @Autowired
    private MockMvc mvc;

    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setupMockWebServer() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void shutdownMockWebServer() throws IOException{
        mockWebServer.shutdown();
    }

    @DynamicPropertySource
    static void backendProps(DynamicPropertyRegistry registry){
        registry.add("basic.url", () -> mockWebServer.url("/").toString());
    }

    @Test
    void getCharacterById_whenCalledWith1_thenReturnRickSanchez() throws Exception{
        //GIVEN
        mockWebServer.enqueue(new MockResponse().setBody("""
                {
                  "id": 1,
                  "name": "Rick Sanchez",
                  "status": "Alive",
                  "species": "Human",
                  "type": "",
                  "gender": "Male"
                }
                """)
                .addHeader("Content-Type", "application/json"));
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/characters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                {
                   "id": 1,
                   "name": "Rick Sanchez",
                   "species": "Human",
                    "status": "Alive"
                }
                """));
    }

}