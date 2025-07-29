package org.example.repository;

import java.util.List;

import org.example.entity.Skill;
import org.example.entity.SkillLevel;
import org.example.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillLevelRepository extends JpaRepository<SkillLevel, Long> {

    List<SkillLevel> findByVolunteer(Volunteer volunteer);

    List<SkillLevel> findBySkill(Skill skill);

    SkillLevel findBySkillAndVolunteer(Skill skill, Volunteer volunteer);

    List<SkillLevel> findByLevel(SkillLevel.Level level);

    @Query("SELECT l " +
            "FROM SkillLevel l " +
            "WHERE l.skill.id = :skillId " +
            "AND l.level = org.example.entity.SkillLevel.Level.ADVANCED")
    List<SkillLevel> findAdvancedVolunteersBySkill(@Param("skillId") Long skillId);

    @Query("SELECT l " +
            "FROM SkillLevel l " +
            "WHERE l.skill.id = :skillId " +
            "AND l.level = :level")
    List<SkillLevel> findBySkillAndLevel(@Param("skillId") Long skillId, @Param("level") SkillLevel.Level level);
}