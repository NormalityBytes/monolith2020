package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.repository.PersonRepository;
import com.mycompany.myapp.repository.UnternehmenRepository;
import com.mycompany.myapp.service.dto.PersonDTO;
import com.mycompany.myapp.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final UnternehmenRepository unternehmenRepository;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper, UnternehmenRepository unternehmenRepository) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.unternehmenRepository = unternehmenRepository;
    }

    /**
     * Save a person.
     *
     * @param personDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        Long unternehmenId = personDTO.getUnternehmenId();
        unternehmenRepository.findById(unternehmenId).ifPresent(person::unternehmen);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    /**
     * Get all the people.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable)
            .map(personMapper::toDto);
    }



    /**
     *  Get all the people where Berater is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonDTO> findAllWhereBeraterIsNull() {
        log.debug("Request to get all people where Berater is null");
        return StreamSupport
            .stream(personRepository.findAll().spliterator(), false)
            .filter(person -> person.getBerater() == null)
            .map(personMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the people where Kandidat is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonDTO> findAllWhereKandidatIsNull() {
        log.debug("Request to get all people where Kandidat is null");
        return StreamSupport
            .stream(personRepository.findAll().spliterator(), false)
            .filter(person -> person.getKandidat() == null)
            .map(personMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     *  Get all the people where Kontakt is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<PersonDTO> findAllWhereKontaktIsNull() {
        log.debug("Request to get all people where Kontakt is null");
        return StreamSupport
            .stream(personRepository.findAll().spliterator(), false)
            .filter(person -> person.getKontakt() == null)
            .map(personMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one person by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id)
            .map(personMapper::toDto);
    }

    /**
     * Delete the person by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
}
