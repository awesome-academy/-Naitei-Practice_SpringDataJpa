package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player() {}

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    @Override
    public String toString() {
        return "ID: " + id + ", Họ tên: " + name + ", Số áo: " + number;
    }
}
