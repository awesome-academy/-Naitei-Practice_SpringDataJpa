package org.example.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    //Quan he
    @OneToMany(mappedBy = "skill")
    private List<SkillLevel> skillLevels;

    //Getter va setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<SkillLevel> getSkillLevels() {
        return skillLevels;
    }
    public void setSkillLevels(List<SkillLevel> skillLevels) {
        this.skillLevels = skillLevels;
    }
}