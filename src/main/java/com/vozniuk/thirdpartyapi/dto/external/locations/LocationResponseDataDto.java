package com.vozniuk.thirdpartyapi.dto.external.locations;

import com.vozniuk.thirdpartyapi.dto.external.GeneralInfoDto;
import java.util.List;
import lombok.Data;

@Data
public class LocationResponseDataDto {
    private GeneralInfoDto info;
    private List<LocationDto> results;
}
