package com.vozniuk.thirdpartyapi.service;

import com.vozniuk.thirdpartyapi.dto.external.characters.CharacterDto;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class ShowCharacterService {
    private final RickAndMortyClient client;
    private final Random random = new Random();

    public ShowCharacterService(RickAndMortyClient client) {
        this.client = client;
    }

    public CharacterDto getRandomCharacter() {
        List<CharacterDto> characters = client.getCharacter();
        int randomId = random.nextInt(client.getCharactersInfo().getCount());
        return characters.get(randomId);
    }

    public List<CharacterDto> searchByName(String searchValue) {
        List<CharacterDto> characters = client.getCharacter();
        return characters.stream()
                .filter(character -> character
                        .name().toLowerCase()
                        .contains(searchValue.toLowerCase()))
                .toList();
    }
}
