package com.culysoft.dio.models.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culysoft.dio.api.dto.PersonInput;
import com.culysoft.dio.models.domain.Person;
import com.culysoft.dio.models.exception.EntidadeNaoEncontradaException;
import com.culysoft.dio.models.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	public Person findById(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Person not found with id: %d", id)));
	}
	
	public Person findByCPF(String cpf) {
		return personRepository.findByCpf(cpf).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Person not found with CPF: %s", cpf)));
	}
	
	public Person save(PersonInput personInput) {
		Person person = toEntity(personInput);
		
		person = person.getId() == null ? add(person) : update(person);
		
		person = personRepository.save(person);
		
		return person;
	}

	private Person add(Person person) {
		validationCPFIfExistOrNot(person.getCpf());
		return person;
	}

	private Person update(Person person) {
		Person personTemp = findById(person.getId());
		
		person.setId(personTemp.getId());
		personTemp = toModel(person);
		
		return personTemp;
	}
	
	private Person toModel(Person person) {
		return modelMapper.map(person, Person.class);
	}

	private void validationCPFIfExistOrNot(String cpf) {
		Optional<Person> person = personRepository.findByCpf(cpf);
		
		if(person.isPresent()) {
			throw new EntidadeNaoEncontradaException(String.format("CPF %s already exists", cpf));
		}
	}

	private Person toEntity(PersonInput personInput) {
		return modelMapper.map(personInput, Person.class);
	}
	
	public Boolean deleteById(Long id) {
		if(personRepository.existsById(id)) {
			personRepository.deleteById(id);
		}
		return false;
	}

}
