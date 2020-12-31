package com.culysoft.dio.models.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.culysoft.dio.models.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Optional<Person> findByCpf(String cpf);

}
