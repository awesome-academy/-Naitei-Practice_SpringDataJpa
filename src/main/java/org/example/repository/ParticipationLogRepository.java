package org.example.repository;

import java.util.List;

import org.example.entity.Campaign;
import org.example.entity.ParticipationLog;
import org.example.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationLogRepository extends JpaRepository<ParticipationLog, Long> {

    List<ParticipationLog> findByVolunteer(Volunteer volunteer);

    List<ParticipationLog> findByCampaign(Campaign campaign);

    List<ParticipationLog> findByRole(String role);

    ParticipationLog findByVolunteerAndCampaign(Volunteer volunteer, Campaign campaign);

    @Query("SELECT COUNT(pl) " +
            "FROM ParticipationLog pl " +
            "WHERE pl.campaign.id = :campaignId")
    Long countVolunteersByCampaign(@Param("campaignId") Long campaignId);
}