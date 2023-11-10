package com.vozniuk.thirdpartyapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vozniuk.thirdpartyapi.dto.external.CharacterExtDto;
import com.vozniuk.thirdpartyapi.dto.external.CharacterResponseDataDto;
import com.vozniuk.thirdpartyapi.dto.external.GeneralInfoDto;
import com.vozniuk.thirdpartyapi.mapper.CharacterMapper;
import com.vozniuk.thirdpartyapi.model.Character;
import com.vozniuk.thirdpartyapi.repository.CharacterRepository;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String CHARACTER_API = "/character";
    private static final String PAGE = "?page=";
    private static final int DEFAULT_PAGE_NUMBER = 1;

    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    //TODO: make data download right after start of applcation
    //@EventListener(ApplicationReadyEvent.class)
    //@Scheduled(cron = "11 * * * * *")
    private void checkIfDataIsSaved() {
        if (characterRepository.count() == 0) {
            saveToRepository();
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    private void updateDataInRepository() {
        List<CharacterExtDto> retrievedData = retrieveDataFromExternalApi();
        List<Character> toBeSavedData = retrievedData.stream()
                .map(characterMapper::toModel)
                .toList();
        List<Character> existingCharacters = characterRepository.findAll();
        //TODO: if externalId from existingCharacters equals id from toBeSavedData - update it,
        // otherwise - save as new item
    }

    private void saveToRepository() {
        characterRepository.saveAll(
                retrieveDataFromExternalApi().stream()
                .map(characterMapper::toModel).toList());
    }

    private List<CharacterExtDto> retrieveDataFromExternalApi() {
        int page = DEFAULT_PAGE_NUMBER;
        List<CharacterExtDto> retrievedData = new ArrayList<>();
        while (getCharactersInfo().getPages() >= page) {
            CharacterResponseDataDto dataDto = extractInformationFromApi(page);
            retrievedData.addAll(dataDto.getResults().stream().toList());
            page += 1;
        }
        return retrievedData;
    }

    private GeneralInfoDto getCharactersInfo() {
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
