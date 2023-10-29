package com.vozniuk.thirdpartyapi.dto.external.characters;

import java.time.LocalDateTime;
import java.util.List;

public record CharacterDto(Long id,
                           String name,
                           String status,
                           String species,
                           String type,
                           String gender,
                           LocationShortInfoDto origin,
                           LocationShortInfoDto location,
                           String image,
                           List<String> episode,
                           String url,
                           LocalDateTime created
                           ) {
}
