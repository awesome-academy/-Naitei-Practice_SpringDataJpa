package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Team;
import org.example.repository.TeamRepo;
import org.example.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Transactional
    public Team saveTeam(Team team) {
        return teamRepo.save(team);
    }

    @Transactional
    public void deleteTeamById(Long id) {
        teamRepo.deleteById(id);
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @Transactional
    public Team getTeamById(Long id) {
        return teamRepo.findById(id).orElse(null);
    }
}
