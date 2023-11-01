package com.vozniuk.thirdpartyapi.controller;

import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import com.vozniuk.thirdpartyapi.service.ShowCharacterServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final ShowCharacterServiceImpl service;

    @GetMapping("/random")
    //@Operation(summary = "Get a random character",
    //        description = "Get a random character from Rick and Morty")
    //        --> ADD MAVEN DEPENDENCY!
    public CharacterIntDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/{searchValue}")
    //@Operation(summary = "Get all characters whose name contain search value",
    //        description = "Get all characters whose name contain search value")
    //        --> ADD MAVEN DEPENDENCY!
    public List<CharacterIntDto> getAllCharactersBySearchValue(@PathVariable String searchValue) {
        return service.searchByName(searchValue);
    }
}
