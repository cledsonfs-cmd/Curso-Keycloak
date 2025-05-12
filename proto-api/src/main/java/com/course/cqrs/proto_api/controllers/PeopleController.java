package com.course.cqrs.proto_api.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.cqrs.proto_api.modals.Person;
import com.course.cqrs.proto_api.servers.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {
    
    private final PeopleService peopleService;

        public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
    
    @Value("${api.version}")
    private String apiVersion;

    @GetMapping("/version")
    public String getVersion() {
        return apiVersion;
    }

    @GetMapping("/create-people/{quantity}")
    public String createPeople(@PathVariable("quantity") Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return "Invalid quantity. Please provide a positive integer.";
        }
        peopleService.generatePeople(quantity);
        return String.format("Created %d people", quantity);
    }

    @GetMapping("/")
    public ResponseEntity<List<Person>> getPeople() {
        return ResponseEntity.ok(peopleService.getPeople());
    }

    @PostMapping("/create-person")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {        
        return ResponseEntity.ok(peopleService.createPerson(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") String id) {
        peopleService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person person) {
        return ResponseEntity.ok(peopleService.updatePerson(id, person));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<List<Person>> getPeopleByName(@PathVariable("name") String name) {        
        return ResponseEntity.ok(peopleService.getPeopleByName(name));
    }
}
