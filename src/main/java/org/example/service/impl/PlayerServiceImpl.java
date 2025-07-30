package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.Player;
import org.example.entity.Team;
import org.example.repository.PlayerRepo;
import org.example.repository.TeamRepo;
import org.example.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private TeamRepo teamRepo;

    @Transactional
    public Player savePlayer(Player player, Long teamId) {
        Team team = teamRepo.findById(teamId).orElse(null);
        if (team != null) {
            team.addPlayer(player);
            teamRepo.save(team); // cascade saves player
            return player;
        }
        return null;
    }

    @Transactional
    public void deletePlayerById(Long id) {
        playerRepo.deleteById(id);
    }

    @Transactional
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @Transactional
    public Player getPlayerById(Long id) {
        return playerRepo.findById(id).orElse(null);
    }
}
