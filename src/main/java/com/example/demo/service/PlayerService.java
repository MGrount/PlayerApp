package com.example.demo.service;

import com.example.demo.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> getAllPlayers();

    Optional<Player> getPlayerById(String playerID);
}
