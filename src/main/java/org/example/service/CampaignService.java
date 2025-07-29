package org.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.example.entity.Campaign;
import org.example.entity.ParticipationLog;
import org.example.entity.Volunteer;
import org.example.repository.CampaignRepository;
import org.example.repository.ParticipationLogRepository;
import org.example.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private ParticipationLogRepository participationLogRepository;


    public Campaign saveCampaign(Campaign campaign){
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    public List<Campaign> findByNameContaining(String name) {
        return campaignRepository.findByNameContaining(name);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    public List<Campaign> findActiveCampaigns() {
        return campaignRepository.findActiveCampaigns();
    }

    public List<Campaign> findByLocation(String location) {
        return campaignRepository.findByLocation(location);
    }

    public List<Campaign> findByDateRange(Date startDate, Date endDate) {
        return campaignRepository.findByStartDateBetween(startDate, endDate);
    }

    public void addVolunteerToCampaign(Long volunteerId, Long campaignId, String role){
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(()-> new RuntimeException("Volunteer not found"));
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(()-> new RuntimeException("Campaign not found"));

        ParticipationLog exist = participationLogRepository.findByVolunteerAndCampaign(volunteer, campaign);

        if (exist!= null){
            throw new RuntimeException("Volunteer already participated in this campaign.");
        }

        ParticipationLog participationLog = new ParticipationLog();
        participationLog.setVolunteer(volunteer);
        participationLog.setCampaign(campaign);
        participationLog.setRole(role);
        participationLog.setJoinedDate(new Date());
        participationLog.setDaysParticipated(0);

        participationLogRepository.save(participationLog);
    }

    public List<ParticipationLog> getCampaignParticipants(Long campaignId){
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(()-> new RuntimeException("Campaign not found"));
        return participationLogRepository.findByCampaign(campaign);
    }

    public Long countCampaignVolunteers(Long campaignId){
        return participationLogRepository.countVolunteersByCampaign(campaignId);
    }

}