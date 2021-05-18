package com.api.cadastro.usuario.personapi.service;

import com.api.cadastro.usuario.personapi.dto.request.PersonDTO;
import com.api.cadastro.usuario.personapi.dto.response.MessageResponseDTO;
import com.api.cadastro.usuario.personapi.entity.Person;
import com.api.cadastro.usuario.personapi.mapper.PersonMapper;
import com.api.cadastro.usuario.personapi.repository.PersonRepository;
import com.api.cadastro.usuario.personapi.service.exception.PersonNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople =  personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public PersonDTO findById(Long id)  {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    @SneakyThrows
    public void delete(Long id) {
         verifyIfExists(id);
        personRepository.deleteById(id);
    }

    @SneakyThrows
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) {
        verifyIfExists(id);
        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID ");
    }

    @SneakyThrows
    private Person verifyIfExists(Long id)  {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }




}

//throws PersonNotFoundException