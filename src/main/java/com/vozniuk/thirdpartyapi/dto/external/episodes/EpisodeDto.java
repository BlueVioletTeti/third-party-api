package com.vozniuk.thirdpartyapi.dto.external.episodes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record EpisodeDto(Long id,
                         String name,
                         @JsonProperty("air_date")
                         String airDate,
                         String episode,
                         List<String> characters,
                         String url,
                         LocalDateTime created
                         ) {
}
