package com.vozniuk.thirdpartyapi.repository;

import com.vozniuk.thirdpartyapi.model.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByNameContainsIgnoreCase(String name);
}
