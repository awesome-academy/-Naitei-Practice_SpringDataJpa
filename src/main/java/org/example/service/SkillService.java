package org.example.service;

import jakarta.transaction.Transactional;
import org.example.entity.Skill;
import org.example.entity.SkillLevel;
import org.example.repository.SkillLevelRepository;
import org.example.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillLevelRepository skillLevelRepository;


    public Skill saveSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill findByName(String name) {
        return skillRepository.findByName(name);
    }

    public List<Skill> findByNameContaining(String name) {
        return skillRepository.findByNameContaining(name);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public List<SkillLevel> findAdvancedVolunteersBySkill(Long skillId) {
        return skillLevelRepository.findAdvancedVolunteersBySkill(skillId);
    }

    public List<SkillLevel> findBySkillAndLevel(Long skillId, SkillLevel.Level level) {
        return skillLevelRepository.findBySkillAndLevel(skillId, level);
    }
}