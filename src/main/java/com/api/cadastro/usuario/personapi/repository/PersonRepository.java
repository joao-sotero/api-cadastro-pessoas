package com.api.cadastro.usuario.personapi.repository;

import com.api.cadastro.usuario.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
