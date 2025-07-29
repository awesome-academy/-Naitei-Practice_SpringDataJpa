package org.example.service;

import java.util.List;
import java.util.Optional;

import org.example.entity.ParticipationLog;
import org.example.entity.Skill;
import org.example.entity.SkillLevel;
import org.example.entity.Volunteer;
import org.example.repository.ParticipationLogRepository;
import org.example.repository.SkillLevelRepository;
import org.example.repository.SkillRepository;
import org.example.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VolunteerService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillLevelRepository skillLevelRepository;

    @Autowired
    ParticipationLogRepository participationLogRepository;


    public Volunteer saveVolunteer(Volunteer volunteer){
        return volunteerRepository.save(volunteer);
    }

    public List<Volunteer> getAllVolunteers(){
        return volunteerRepository.findAll();
    }

    public Optional<Volunteer> getVolunteerById(Long id){
        return volunteerRepository.findById(id);
    }

    public Volunteer findByEmail(String email){
        return volunteerRepository.findByEmail(email);
    }

    public List<Volunteer> findByFullNameContaining(String name) {
        return volunteerRepository.findByFullNameContaining(name);
    }

    public void deleteVolunteer(Long id){
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        
        // Xóa các SkillLevel liên quan trước
        List<SkillLevel> skillLevels = skillLevelRepository.findByVolunteer(volunteer);
        skillLevelRepository.deleteAll(skillLevels);
        
        // Xóa các ParticipationLog liên quan
        List<ParticipationLog> participationLogs = participationLogRepository.findByVolunteer(volunteer);
        participationLogRepository.deleteAll(participationLogs);
        
        // Cuối cùng xóa volunteer
        volunteerRepository.deleteById(id);
    }

    public List<Volunteer> findVolunteerBySkillName(String name){
        return volunteerRepository.findBySkillName(name);
    }

    public List<Volunteer> findActiveVolunteers(){
        return volunteerRepository.findActiveVolunteers();
    }

    public void assignSkillToVolunteer(Long volunteerId, Long skillId, SkillLevel.Level level) {

        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(()-> new RuntimeException("Volunteer not found!"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(()-> new RuntimeException("Skill not found!"));

        SkillLevel existingSkillLevel = skillLevelRepository.findBySkillAndVolunteer(skill, volunteer);

        if (existingSkillLevel != null){
            existingSkillLevel.setLevel(level);
        }
        else {
            SkillLevel skillLevel = new SkillLevel();
            skillLevel.setVolunteer(volunteer);
            skillLevel.setSkill(skill);
            skillLevel.setLevel(level);
            skillLevelRepository.save(skillLevel);
        }
    }

    public List<SkillLevel> getVolunteerSkillLevels(Long volunteerId){
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
        return skillLevelRepository.findByVolunteer(volunteer);
    }
}