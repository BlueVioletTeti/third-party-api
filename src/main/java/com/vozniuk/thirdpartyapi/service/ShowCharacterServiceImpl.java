package com.vozniuk.thirdpartyapi.service;

import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import com.vozniuk.thirdpartyapi.mapper.CharacterMapper;
import com.vozniuk.thirdpartyapi.model.Character;
import com.vozniuk.thirdpartyapi.repository.CharacterRepository;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShowCharacterServiceImpl implements ShowCharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @Override
    public CharacterIntDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        int randomId = random.nextInt((int) characterRepository.count());
        return characterMapper.toDto(characters.get(randomId));
    }

    @Override
    public List<CharacterIntDto> searchByName(String searchValue) {
        List<Character> characters = characterRepository.findAllByNameContains(searchValue);
        return characters.stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
