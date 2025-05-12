package com.course.cqrs.proto_api.servers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.course.cqrs.proto_api.modals.Person;
import com.course.cqrs.proto_api.servers.PeopleService;
import com.github.javafaker.Faker;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final List<Person> listOfPeople = new ArrayList<>();
    private final Faker faker = new Faker();

    @Override
    public List<Person> getPeople() {        
        return listOfPeople;
    }

    @Override
    public Optional<Person> getPersonById(String id) {
        return listOfPeople.stream()
            .filter(person -> person.getId().equals(id))
            .findFirst();
    }

    @Override
    public Person createPerson(Person person) {
        Person newPerson = Person.builder()
            .id(UUID.randomUUID().toString())
            .fullName(person.getFullName())
            .birthDate(person.getBirthDate())
            .age(person.getAge())
            .build();
        listOfPeople.add(newPerson);
        return newPerson;
    }

    @Override
    public Person updatePerson(String id, Person person) {
        Person findPerson = getPersonById(id).orElseThrow(() -> new IllegalArgumentException("Person not found"));
        listOfPeople.remove(findPerson);
        Person updatedPerson = changePerson(id, person);
        listOfPeople.add(updatedPerson);
        return updatedPerson;
    }

    private Person changePerson(String id,Person person) {
        return Person.builder()
            .id(id)
            .fullName(person.getFullName())
            .birthDate(person.getBirthDate())
            .age(person.getAge())
            .build();
    }

    @Override
    public void deletePerson(String id) {
        Person findPerson = getPersonById(id).orElseThrow(() -> new IllegalArgumentException("Person not found"));
        listOfPeople.remove(findPerson);
    }

    @Override
    public List<Person> getPeopleByName(String name) {
        return listOfPeople.stream()
            .filter(person -> person.getFullName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }

    @Override
    public void generatePeople(Integer quantity) {
        if(quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be a positive integer.");
        }
        listOfPeople.clear(); 
        for (int i = 0; i < quantity; i++) {
            Person person = Person.builder()
            .id(UUID.randomUUID().toString())
            .fullName(faker.name().fullName())
            .birthDate(faker.date().birthday())
            .age(faker.number().numberBetween(1,100))
            .build();
            listOfPeople.add(person);
        }
    }
}
