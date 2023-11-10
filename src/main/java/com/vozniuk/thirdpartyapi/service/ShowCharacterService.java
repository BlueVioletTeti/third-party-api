package com.vozniuk.thirdpartyapi.service;

import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ShowCharacterService {
    CharacterIntDto getRandomCharacter();

    List<CharacterIntDto> getByName(String searchValue, Pageable pageable);
}
