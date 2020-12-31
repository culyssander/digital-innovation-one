package com.culysoft.dio.api.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;


import lombok.Data;

@Data
public class PersonInput {

	private Long id;

	@NotBlank
	@Size(min = 2, max = 100)
	private String firstName;

	@NotBlank
	@Size(min = 2, max = 100)
	private String lastName;

	@NotBlank
//	@CPF
	private String cpf;

	@NotNull
	private LocalDate birthDate;

	@Valid
	@NotNull
	private List<PhoneInput> phones;

}
