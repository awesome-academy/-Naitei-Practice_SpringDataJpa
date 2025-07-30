package org.example.service;

import org.example.entity.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);
    List<Team> getAllTeams();
    Team getTeamById(Long id);
    void deleteTeamById(Long id);
}

