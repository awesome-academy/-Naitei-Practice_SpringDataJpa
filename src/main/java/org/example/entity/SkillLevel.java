package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_levels")
public class SkillLevel {
    public enum Level{
        BEGINNER, INTERMEDIATE, ADVANCED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level")
    private Level level;

    //Quan he
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    //Getter va setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Level getLevel() {
        return level;
    }
    public void setLevel(Level level) {
        this.level = level;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public Skill getSkill() {
        return skill;
    }
    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}