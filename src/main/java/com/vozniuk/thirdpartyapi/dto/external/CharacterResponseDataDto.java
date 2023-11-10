package com.vozniuk.thirdpartyapi.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterResponseDataDto {
    private GeneralInfoDto info;
    private List<CharacterExtDto> results;
}
