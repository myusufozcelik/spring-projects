package com.ozcelik.service;

import com.ozcelik.dto.PersonDto;
import com.ozcelik.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PersonService {

    PersonDto save(PersonDto personDto);

    void delete(Long id);

    List<PersonDto> getAll();

    Page<Person> getAll(Pageable pageable);
}
