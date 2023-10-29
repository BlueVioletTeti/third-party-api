package com.vozniuk.thirdpartyapi.dto.external.characters;

import com.vozniuk.thirdpartyapi.dto.external.GeneralInfoDto;
import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private GeneralInfoDto info;
    private List<CharacterDto> results;
}
