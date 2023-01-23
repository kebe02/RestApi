package com.owt.challenge.controller;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SkillControllerTests extends BaseIntegrationTest {

    @Test
    void createSkillTest()
    throws URISyntaxException {

        ResponseEntity<Skill> result = createSkill("name", 1);
        assertEquals(200, result.getStatusCodeValue());

        var createdSkill = result.getBody();
        assertNotNull(createdSkill.getId());
        assertEquals("name", createdSkill.getName());
        assertEquals(1, createdSkill.getLevel());
    }

    @Test
    void readSkillTest()
    throws URISyntaxException {

        ResponseEntity<Skill> createResult = createSkill("name", 1);
        assertEquals(200, createResult.getStatusCodeValue());
        var createdSkill = createResult.getBody();

        ResponseEntity<Skill> readResult = readSkill(createdSkill.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        var readSkill = readResult.getBody();
        assertEquals(createdSkill.getId(), readSkill.getId());
        assertEquals(createdSkill.getName(), readSkill.getName());
        assertEquals(createdSkill.getLevel(), readSkill.getLevel());
    }

    @Test
    void updateSkillTest()
    throws URISyntaxException {

        ResponseEntity<Skill> createResult = createSkill("name", 1);
        assertEquals(200, createResult.getStatusCodeValue());
        var createdSkill = createResult.getBody();

        ResponseEntity<String> updateResult = updateSkill(createdSkill.getId(), "updated", 2);
        assertEquals(200, createResult.getStatusCodeValue());

        ResponseEntity<Skill> readResult = readSkill(createdSkill.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        var readSkill = readResult.getBody();
        assertEquals(createdSkill.getId(), readSkill.getId());
        assertEquals("updated", readSkill.getName());
        assertEquals(2, readSkill.getLevel());
    }

    @Test
    void deleteSkillTest()
    throws URISyntaxException {

        ResponseEntity<Skill> createResult = createSkill("name", 1);
        assertEquals(200, createResult.getStatusCodeValue());
        var createdContact = createResult.getBody();

        ResponseEntity<Skill> readResult = readSkill(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        ResponseEntity<String> deleteResponse = deleteSkill(createdContact.getId());
        assertEquals(200, readResult.getStatusCodeValue());

        ResponseEntity<Skill> readResultAfterDelete = readSkill(createdContact.getId());
        assertEquals(404, readResultAfterDelete.getStatusCodeValue());
    }

    @Test
    void addAndRemoveContact()
            throws URISyntaxException {

        ResponseEntity<Contact> createContactResult = createContact("firstname", "lastname", "fullname", "address", "email@test.com", "0785555555");
        assertEquals(200, createContactResult.getStatusCodeValue());
        var createdContact = createContactResult.getBody();

        ResponseEntity<Skill> createSkillResult = createSkill("name", 1);
        assertEquals(200, createSkillResult.getStatusCodeValue());
        var createdSkill = createSkillResult.getBody();

        ResponseEntity<String> addSkillResponse = addContactToSkill(createdSkill.getId(), createdContact.getId());
        assertEquals(200, addSkillResponse.getStatusCodeValue());

        ResponseEntity<Skill> readSkillWithContactResponse = readSkill(createdSkill.getId());
        assertEquals(200, readSkillWithContactResponse.getStatusCodeValue());
        var readSkillWithContact = readSkillWithContactResponse.getBody();
        assertEquals(1, readSkillWithContact.getContacts().size());

        ResponseEntity<String> removeContactResponse = deleteContactFromSkill(createdSkill.getId(), createdContact.getId());
        assertEquals(200, removeContactResponse.getStatusCodeValue());

        ResponseEntity<Skill> readSkillWithoutContactResponse = readSkill(createdSkill.getId());
        assertEquals(200, readSkillWithoutContactResponse.getStatusCodeValue());
        var readSkillWithoutContact = readSkillWithoutContactResponse.getBody();
        assertEquals(0, readSkillWithoutContact.getContacts().size());
    }

}
