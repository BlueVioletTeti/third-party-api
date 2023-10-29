package com.vozniuk.thirdpartyapi.dto.external.locations;

import java.time.LocalDateTime;
import java.util.List;

public record LocationDto(Long id,
                          String name,
                          String type,
                          String dimension,
                          List<String> residents,
                          String url,
                          LocalDateTime created
                          ) {
}
