package org.example.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "participation_logs")
public class ParticipationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "joined_date")
    private Date joinedDate;

    @Column(name = "days_participated")
    private int daysParticipated;

    //Quan he
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    //Getter va setter
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }
    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public int getDaysParticipated() {
        return daysParticipated;
    }
    public void setDaysParticipated(int daysParticipated) {
        this.daysParticipated = daysParticipated;
    }

    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }
}