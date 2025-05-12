package com.course.cqrs.proto_api.servers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.course.cqrs.proto_api.modals.Person;

@Service
public interface PeopleService {

    List<Person> getPeople();

    Optional<Person> getPersonById(String id);

    Person createPerson(Person person);

    Person updatePerson(String id, Person person);

    void deletePerson(String id);

    List<Person> getPeopleByName(String name);

    void generatePeople(Integer quantity);

}
