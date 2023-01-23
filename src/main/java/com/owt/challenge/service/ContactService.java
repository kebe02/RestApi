package com.owt.challenge.service;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.repository.ContactRepository;
import com.owt.challenge.repository.SkillRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    private final SkillRepository skillRepository;

    public Contact createContact(Contact newContact) {
        return contactRepository.save(newContact);
    }

    public Optional<Contact> readContact(Long id) {
        return contactRepository.findById(id);
    }

    public Optional<Contact> updateContact(Long id, Contact newContact) {

        var optionalContact = contactRepository.findById(id);
        if(optionalContact.isEmpty()) {
            return Optional.empty();
        }

        var foundContact = optionalContact.get();
        foundContact.setFirstname(newContact.getFirstname());
        foundContact.setLastname(newContact.getLastname());
        foundContact.setFullname(newContact.getFullname());
        foundContact.setAddress(newContact.getAddress());
        foundContact.setEmail(newContact.getEmail());
        foundContact.setMobile(newContact.getMobile());

        var savedContact = contactRepository.save(foundContact);
        return Optional.of(savedContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Optional<Contact> addSkill(Long contactId, Long skillId) {

        var optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty()) {
            return Optional.empty();
        }

        var optionalSkill = skillRepository.findById(skillId);
        if(optionalSkill.isEmpty()) {
            return Optional.empty();
        }

        var contact = optionalContact.get();
        var skill = optionalSkill.get();
        contact.getSkills().add(skill);
        return Optional.of(contactRepository.save(contact));
    }

    public Optional<Contact> removeSkill(Long contactId, Long skillId) {

        var optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty()) {
            return Optional.empty();
        }

        var optionalSkill = skillRepository.findById(skillId);
        if(optionalSkill.isEmpty()) {
            return Optional.empty();
        }

        var contact = optionalContact.get();
        var skill = optionalSkill.get();
        contact.getSkills().remove(skill);
        return Optional.of(contactRepository.save(contact));
    }
}
