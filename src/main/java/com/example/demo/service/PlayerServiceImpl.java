package com.example.demo.service;


import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<Player> getAllPlayers() {
        log.info("getAllPlayers");
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> getPlayerById(String playerID) {
        log.info("getPlayerById -> PlayerID: " + playerID);
        return playerRepository.findById(playerID);
    }
}
