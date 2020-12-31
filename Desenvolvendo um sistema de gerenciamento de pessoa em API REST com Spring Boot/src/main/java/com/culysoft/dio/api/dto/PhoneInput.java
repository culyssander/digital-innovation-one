package com.culysoft.dio.api.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.culysoft.dio.models.enums.PhoneType;

import lombok.Data;

@Data
public class PhoneInput {

	private Long id;

    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @NotBlank
    @Size(min = 13, max = 14)
    private String number;
}
