package com.owt.challenge.service;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import com.owt.challenge.repository.ContactRepository;
import com.owt.challenge.repository.SkillRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    private final ContactRepository contactRepository;

    public Skill createSkill(Skill newSkill) {
        return skillRepository.save(newSkill);
    }

    public Optional<Skill> readSkill(Long id) {
        return skillRepository.findById(id);
    }

    public Optional<Skill> updateSkill(Long id, Skill newSkill) {

        var optionalSkill = skillRepository.findById(id);
        if(optionalSkill.isEmpty()) {
            return Optional.empty();
        }

        var foundSkill = optionalSkill.get();
        foundSkill.setName(newSkill.getName());
        foundSkill.setLevel(newSkill.getLevel());

        var savedSkill = skillRepository.save(foundSkill);
        return Optional.of(savedSkill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public Optional<Skill> addContact(Long skillId, Long contactId) {

        var optionalSkill = skillRepository.findById(skillId);
        if(optionalSkill.isEmpty()) {
            return Optional.empty();
        }

        var optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty()) {
            return Optional.empty();
        }

        var contact = optionalContact.get();
        var skill = optionalSkill.get();
        skill.getContacts().add(contact);
        return Optional.of(skillRepository.save(skill));
    }

    public Optional<Skill> removeContact(Long skillId,Long contactId) {

        var optionalSkill = skillRepository.findById(skillId);
        if(optionalSkill.isEmpty()) {
            return Optional.empty();
        }

        var optionalContact = contactRepository.findById(contactId);
        if(optionalContact.isEmpty()) {
            return Optional.empty();
        }

        var contact = optionalContact.get();
        var skill = optionalSkill.get();
        skill.getContacts().remove(contact);
        return Optional.of(skillRepository.save(skill));
    }
}
