package com.vozniuk.thirdpartyapi.controller;

import com.vozniuk.thirdpartyapi.dto.external.characters.CharacterDto;
import com.vozniuk.thirdpartyapi.service.ShowCharacterService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {
    private final ShowCharacterService service;

    public CharacterController(ShowCharacterService service) {
        this.service = service;
    }

    @GetMapping("/random")
    //@Operation(summary = "Get a random character",
    //        description = "Get a random character from Rick and Morty")
    //        --> ADD MAVEN DEPENDENCY!
    public CharacterDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/{searchValue}")
    //@Operation(summary = "Get all characters whose name contain search value",
    //        description = "Get all characters whose name contain search value")
    //        --> ADD MAVEN DEPENDENCY!
    public List<CharacterDto> getAllCharactersBySearchValue(@PathVariable String searchValue) {
        return service.searchByName(searchValue);
    }
}
