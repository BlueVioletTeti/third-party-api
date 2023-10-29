package com.vozniuk.thirdpartyapi.dto.external;

import lombok.Data;

@Data
public class GeneralInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
