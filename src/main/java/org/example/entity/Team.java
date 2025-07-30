package org.example.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Player> players = new ArrayList<>();

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Player> getPlayers() { return players; }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.setTeam(null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID đội bóng: ").append(id).append(", Tên đội bóng: ").append(name).append("\n");

        if (players == null || players.isEmpty()) {
            sb.append("  -> Không có cầu thủ trong đội bóng này.\n");
        } else {
            for (Player player : players) {
                sb.append("  -> ID cầu thủ: ").append(player.getId())
                        .append(", Họ tên: ").append(player.getName())
                        .append(", Số áo: ").append(player.getNumber()).append("\n");
            }
        }

        return sb.toString();
    }
}
