package com.vozniuk.thirdpartyapi.dto.external.episodes;

import com.vozniuk.thirdpartyapi.dto.external.GeneralInfoDto;
import java.util.List;
import lombok.Data;

@Data
public class EpisodeResponseDataDto {
    private GeneralInfoDto info;
    private List<EpisodeDto> results;
}
