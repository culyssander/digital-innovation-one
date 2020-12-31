package com.culysoft.dio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.culysoft.dio.api.dto.PersonInput;
import com.culysoft.dio.models.domain.Person;
import com.culysoft.dio.models.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping
	public List<Person> findAll() {
		return personService.findAll();
	}
	
	@GetMapping("{id}")
	public Person findById(@PathVariable Long id) {
		return personService.findById(id);
	}
	
	@GetMapping("/cpf/{cpf}")
	public Person findById(@PathVariable String cpf) {
		return personService.findByCPF(cpf);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Person add(@Validated @RequestBody PersonInput personInput) {
		personInput.setId(null);
		return personService.save(personInput);
	}
	
	@PutMapping("{id}")
	public Person update(@Validated @PathVariable Long id, @RequestBody PersonInput personInput) {
		personInput.setId(id);
		return personService.save(personInput);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		
		if(personService.deleteById(id)) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
