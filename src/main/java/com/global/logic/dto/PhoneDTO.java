package com.global.logic.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhoneDTO {
    private Long number;
    private int cityCode;
    private String countryCode;
}
