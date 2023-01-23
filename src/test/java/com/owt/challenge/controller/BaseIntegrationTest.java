package com.owt.challenge.controller;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    protected ResponseEntity<Contact> createContact(String firstname, String lastname, String fullname, String address, String email, String mobile)
            throws URISyntaxException {

        Contact contact = new Contact();
        contact.setFirstname(firstname);
        contact.setLastname(lastname);
        contact.setFullname(fullname);
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setMobile(mobile);

        URI uri = new URI("http://localhost:" + port + "/contacts/");
        return restTemplate.postForEntity(uri, contact, Contact.class);
    }

    protected  ResponseEntity<Contact> readContact(long id)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/contacts/" + id);
        return restTemplate.getForEntity(uri, Contact.class);
    }

    protected ResponseEntity<String> updateContact(long id, String firstname, String lastname, String fullname, String address, String email, String mobile)
            throws URISyntaxException {

        Contact contact = new Contact();
        contact.setFirstname(firstname);
        contact.setLastname(lastname);
        contact.setFullname(fullname);
        contact.setAddress(address);
        contact.setEmail(email);
        contact.setMobile(mobile);

        URI uri = new URI("http://localhost:" + port + "/contacts/" + id);
        return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(contact), String.class);
    }

    protected ResponseEntity<String> deleteContact(long id)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/contacts/" + id);
        return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    protected ResponseEntity<String> addSkillToContact(long contactId, long skillId)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/contacts/" + contactId + "/skills/" + skillId);
        return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    protected ResponseEntity<String> deleteSkillFromContact(long contactId, long skillId)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/contacts/" + contactId + "/skills/" + skillId);
        return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    protected  ResponseEntity<Skill> createSkill(String name, int level)
            throws URISyntaxException {

        Skill skill = new Skill();
        skill.setName(name);
        skill.setLevel(level);

        URI uri = new URI("http://localhost:" + port + "/skills/");
        return restTemplate.postForEntity(uri, skill, Skill.class);
    }

    protected  ResponseEntity<Skill> readSkill(long id)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/skills/" + id);
        return restTemplate.getForEntity(uri, Skill.class);
    }

    protected ResponseEntity<String> updateSkill(long id, String name, int level)
            throws URISyntaxException {

        Skill skill = new Skill();
        skill.setName(name);
        skill.setLevel(level);

        URI uri = new URI("http://localhost:" + port + "/skills/" + id);
        return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(skill), String.class);
    }

    protected ResponseEntity<String> deleteSkill(long id)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/skills/" + id);
        return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    protected ResponseEntity<String> addContactToSkill(long skillId, long contactId)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/skills/" + skillId + "/contacts/" + contactId);
        return restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(new HttpHeaders()), String.class);
    }

    protected ResponseEntity<String> deleteContactFromSkill(long skillId, long contactId)
            throws URISyntaxException {

        URI uri = new URI("http://localhost:" + port + "/skills/" + skillId + "/contacts/" + contactId);
        return restTemplate.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), String.class);
    }

}
