package com.ozcelik.service.impl;

import com.ozcelik.dto.PersonDto;
import com.ozcelik.entity.Address;
import com.ozcelik.entity.Person;
import com.ozcelik.repository.AddressRepository;
import com.ozcelik.repository.PersonRepository;
import com.ozcelik.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        //assert (personDto.getFirstName().isEmpty() || personDto.getFirstName() == null) : "Firstname is required";
        Person person = Person.builder().firstName(personDto.getFirstName())
                .lastName(personDto.getLastName()).build();
        final Person personDb = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();
        personDto.getAddress().forEach(item -> {
            Address address = Address.builder().address(item)
                    .addressType(Address.AddressType.OTHER)
                    .active(true)
                    .person(personDb).build();
            addressList.add(address);
        });

        addressRepository.saveAll(addressList);
        personDto.setId(personDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> people = personRepository.findAll();
        List<PersonDto> personDtoList = new ArrayList<>();

        people.forEach(p -> {
            PersonDto personDto = new PersonDto().builder()
                    .id(p.getId())
                    .firstName(p.getFirstName())
                    .lastName(p.getLastName())
                    .address(p.getAddress().stream().map(Address::getAddress).collect(Collectors.toList()))
                    .build();
            personDtoList.add(personDto);
        });
        return personDtoList;
    }

    @Override
    public Page<Person> getAll(Pageable pageable) {
        return null;
    }
}
