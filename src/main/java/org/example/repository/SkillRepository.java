package org.example.repository;

import java.util.List;

import org.example.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    Skill findByName(String skill);

    List<Skill> findByNameContaining(String skill);
}