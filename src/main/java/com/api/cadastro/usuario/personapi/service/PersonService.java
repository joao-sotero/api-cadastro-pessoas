package com.api.cadastro.usuario.personapi.service;

import com.api.cadastro.usuario.personapi.dto.request.PersonDTO;
import com.api.cadastro.usuario.personapi.dto.response.MessageResponseDTO;
import com.api.cadastro.usuario.personapi.entity.Person;
import com.api.cadastro.usuario.personapi.mapper.PersonMapper;
import com.api.cadastro.usuario.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson( PersonDTO personDTO){
        Person personToSaved = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSaved);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

}
