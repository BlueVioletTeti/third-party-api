package com.vozniuk.thirdpartyapi.controller;

import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import com.vozniuk.thirdpartyapi.service.ShowCharacterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/character")
public class CharacterController {
    private final ShowCharacterServiceImpl service;

    @GetMapping("/random")
    @Operation(summary = "Get a random character",
            description = "Get a random character from Rick and Morty")
    public CharacterIntDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get all characters whose name contain search value",
            description = "Get all characters whose name contain search value")
    public List<CharacterIntDto> getAllCharactersByName(
            @RequestParam String name, Pageable pageable) {
        return service.getByName(name, pageable);
    }
}
