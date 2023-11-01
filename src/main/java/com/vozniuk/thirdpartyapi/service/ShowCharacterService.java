package com.vozniuk.thirdpartyapi.service;

import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import java.util.List;

public interface ShowCharacterService {
    CharacterIntDto getRandomCharacter();

    List<CharacterIntDto> searchByName(String searchValue);
}
