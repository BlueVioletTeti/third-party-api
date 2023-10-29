package com.vozniuk.thirdpartyapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vozniuk.thirdpartyapi.dto.external.GeneralInfoDto;
import com.vozniuk.thirdpartyapi.dto.external.characters.CharacterDto;
import com.vozniuk.thirdpartyapi.dto.external.characters.CharacterResponseDataDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String CHARACTER_API = "/character";
    private static final String LOCATION_API = "/location";
    private static final String EPISODES_API = "/episode";
    private static final String PAGE = "?page=";
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private final ObjectMapper objectMapper;

    public RickAndMortyClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CharacterDto> getCharacter() {
        int page = DEFAULT_PAGE_NUMBER;
        List<CharacterDto> resultList = new ArrayList<>();
        while (getCharactersInfo().getPages() >= page) {
            CharacterResponseDataDto dataDto = extractInformationFromApi(page);
            resultList.addAll(dataDto.getResults().stream().toList());
            page += 1;
        }
        return resultList;
    }

    public GeneralInfoDto getCharactersInfo() {
        CharacterResponseDataDto dataDto = extractInformationFromApi(DEFAULT_PAGE_NUMBER);
        return dataDto.getInfo();
    }

    private CharacterResponseDataDto extractInformationFromApi(int pageNumber) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL + CHARACTER_API + PAGE + pageNumber;

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response
                    = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
