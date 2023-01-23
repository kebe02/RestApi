package com.owt.challenge.controller;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ContactControllerTests extends BaseIntegrationTest {


    @Test
    void createContactTest()
    throws URISyntaxException {

        ResponseEntity<Contact> result = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, result.getStatusCodeValue());

        var createdContact = result.getBody();
        assertNotNull(createdContact.getId());
        assertEquals("firstname", createdContact.getFirstname());
        assertEquals("lastname", createdContact.getLastname());
        assertEquals("fullname", createdContact.getFullname());
        assertEquals("address", createdContact.getAddress());
        assertEquals("email@test.com", createdContact.getEmail());
        assertEquals("0785555555", createdContact.getMobile());
    }

    @Test
    void readContactTest()
    throws URISyntaxException {

        ResponseEntity<Contact> createResult = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, createResult.getStatusCodeValue());
        var createdContact = createResult.getBody();

        ResponseEntity<Contact> readResult = readContact(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        var readContact = readResult.getBody();
        assertEquals(createdContact.getId(), readContact.getId());
        assertEquals(createdContact.getFirstname(), readContact.getFirstname());
        assertEquals(createdContact.getLastname(), readContact.getLastname());
        assertEquals(createdContact.getFullname(), readContact.getFullname());
        assertEquals(createdContact.getAddress(), readContact.getAddress());
        assertEquals(createdContact.getEmail(), readContact.getEmail());
        assertEquals(createdContact.getMobile(), readContact.getMobile());
    }

    @Test
    void updateContactTest()
    throws URISyntaxException {

        ResponseEntity<Contact> createResult = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, createResult.getStatusCodeValue());
        var createdContact = createResult.getBody();

        ResponseEntity<String> updateResult = updateContact(createdContact.getId(), "updated", "updated", "updated", "updated", "updated@test.com", "0786666666");
        assertEquals(200, createResult.getStatusCodeValue());

        ResponseEntity<Contact> readResult = readContact(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        var readContact = readResult.getBody();
        assertEquals(createdContact.getId(), readContact.getId());
        assertEquals("updated", readContact.getFirstname());
        assertEquals("updated", readContact.getLastname());
        assertEquals("updated", readContact.getFullname());
        assertEquals("updated", readContact.getAddress());
        assertEquals("updated@test.com", readContact.getEmail());
        assertEquals("0786666666", readContact.getMobile());
    }

    @Test
    void deleteContactTest()
    throws URISyntaxException {

        ResponseEntity<Contact> createResult = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, createResult.getStatusCodeValue());
        var createdContact = createResult.getBody();

        ResponseEntity<Contact> readResult = readContact(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        ResponseEntity<String> deleteResponse = deleteContact(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        ResponseEntity<Contact> readResultAfterDelete = readContact(createdContact.getId());
        assertEquals(404, readResultAfterDelete.getStatusCodeValue());
    }

    @Test
    void addAndRemoveSkill()
    throws URISyntaxException {

        ResponseEntity<Contact> createContactResult = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, createContactResult.getStatusCodeValue());
        var createdContact = createContactResult.getBody();

        ResponseEntity<Skill> createSkillResult = createSkill("name", 1);
        assertEquals(200, createSkillResult.getStatusCodeValue());
        var createdSkill = createSkillResult.getBody();

        ResponseEntity<String> addSkillResponse = addSkillToContact(createdContact.getId(), createdSkill.getId());
        assertEquals(200, addSkillResponse.getStatusCodeValue());

        ResponseEntity<Contact> readContactWithSkillResponse = readContact(createdContact.getId());
        assertEquals(200, readContactWithSkillResponse.getStatusCodeValue());
        var readContactWithSkill = readContactWithSkillResponse.getBody();
        assertEquals(1, readContactWithSkill.getSkills().size());

        ResponseEntity<String> removeSkillResponse = deleteSkillFromContact(createdContact.getId(), createdSkill.getId());
        assertEquals(200, addSkillResponse.getStatusCodeValue());

        ResponseEntity<Contact> readContactWithoutSkillResponse = readContact(createdContact.getId());
        assertEquals(200, readContactWithoutSkillResponse.getStatusCodeValue());
        var readContactWithoutSkill = readContactWithoutSkillResponse.getBody();
        assertEquals(0, readContactWithoutSkill.getSkills().size());
    }

}
