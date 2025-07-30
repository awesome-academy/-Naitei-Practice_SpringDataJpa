package org.example.service;

import org.example.entity.Player;

import java.util.List;

public interface PlayerService {
    Player savePlayer(Player player, Long teamId);
    List<Player> getAllPlayers();
    Player getPlayerById(Long id);
    void deletePlayerById(Long id);
}
