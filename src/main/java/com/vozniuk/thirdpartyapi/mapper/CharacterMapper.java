package com.vozniuk.thirdpartyapi.mapper;

import com.vozniuk.thirdpartyapi.config.MapperConfig;
import com.vozniuk.thirdpartyapi.dto.external.CharacterExtDto;
import com.vozniuk.thirdpartyapi.dto.internal.characters.CharacterIntDto;
import com.vozniuk.thirdpartyapi.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterIntDto toDto(Character character);

    Character toModel(CharacterExtDto character);
}
