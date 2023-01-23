package com.owt.challenge.controller;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/")
    ResponseEntity<Contact> create(@Valid @RequestBody Contact contact) {

        var savedContact = contactService.createContact(contact);
        return ResponseEntity.ok(savedContact);
    }

    @GetMapping("/{id}")
    ResponseEntity<Contact> read(@PathVariable Long id) {

        var foundContact = contactService.readContact(id);
        if(foundContact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(foundContact.get());
    }

    @PutMapping("/{id}")
    ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody Contact contact) {

        var updatedContact = contactService.updateContact(id, contact);
        if(updatedContact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>  delete(@PathVariable Long id) {

        var foundContact = contactService.readContact(id);
        if(foundContact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contactService.deleteContact(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{contactId}/skills/{skillId}")
    ResponseEntity<String> addSkill(@PathVariable Long contactId, @PathVariable Long skillId) {

        var updatedContact = contactService.addSkill(contactId, skillId);
        if(updatedContact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{contactId}/skills/{skillId}")
    ResponseEntity<String> removeSkill(@PathVariable Long contactId, @PathVariable Long skillId) {

        var updatedContact = contactService.removeSkill(contactId, skillId);
        if(updatedContact.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }
}
