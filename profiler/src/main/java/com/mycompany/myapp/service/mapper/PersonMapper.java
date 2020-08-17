package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Person} and its DTO {@link PersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {UnternehmenMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "unternehmen.id", target = "unternehmenId")
    PersonDTO toDto(Person person);

    @Mapping(source = "unternehmenId", target = "unternehmen")
    @Mapping(target = "kontaktinfos", ignore = true)
    @Mapping(target = "removeKontaktinfo", ignore = true)
    @Mapping(target = "adresses", ignore = true)
    @Mapping(target = "removeAdresse", ignore = true)
    @Mapping(target = "berater", ignore = true)
    @Mapping(target = "kandidat", ignore = true)
    @Mapping(target = "kontakt", ignore = true)
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
