package org.example.entity;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "join_date")
    private Date joinDate;

    //Quan he
    @OneToMany(mappedBy = "volunteer", cascade = CascadeType.ALL)
    private List<ParticipationLog> participationLogs;

    @OneToMany(mappedBy = "volunteer")
    private List<SkillLevel> skillLevels;

    //Getter va setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getJoinDate() {
        return joinDate;
    }
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public List<ParticipationLog> getParticipationLogs() {
        return participationLogs;
    }
    public void setParticipationLogs(List<ParticipationLog> participationLogs) {
        this.participationLogs = participationLogs;
    }

    public List<SkillLevel> getSkillLevels() {
        return skillLevels;
    }
    public void setSkillLevels(List<SkillLevel> skillLevels) {
        this.skillLevels = skillLevels;
    }
}