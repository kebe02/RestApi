package com.owt.challenge.controller;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import com.owt.challenge.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/")
    ResponseEntity<Skill> create(@Valid @RequestBody Skill skill) {

        var savedSkill = skillService.createSkill(skill);
        return ResponseEntity.ok(savedSkill);
    }

    @GetMapping("/{id}")
    ResponseEntity<Skill> read(@PathVariable Long id) {

        var foundSkill = skillService.readSkill(id);
        if(foundSkill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(foundSkill.get());
    }

    @PutMapping("/{id}")
    ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody Skill skill) {

        var updatedSkill = skillService.updateSkill(id, skill);
        if(updatedSkill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>  delete(@PathVariable Long id) {

        var foundSkill = skillService.readSkill(id);
        if(foundSkill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        skillService.deleteSkill(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{skillId}/contacts/{contactId}")
    ResponseEntity<String> addSkill(@PathVariable Long skillId, @PathVariable Long contactId) {

        var updatedSkill = skillService.addContact(skillId, contactId);
        if(updatedSkill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{skillId}/contacts/{contactId}")
    ResponseEntity<String> removeSkill(@PathVariable Long skillId, @PathVariable Long contactId) {

        var updatedSkill = skillService.removeContact(skillId, contactId);
        if(updatedSkill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok("");
    }
}
