package com.owt.challenge.repository;

import com.owt.challenge.entity.Contact;
import com.owt.challenge.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

}