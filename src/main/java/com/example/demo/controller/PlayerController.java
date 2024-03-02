package com.example.demo.controller;

import com.example.demo.entity.Player;
import com.example.demo.exception.ValidationException;
import com.example.demo.mapper.PlayerMapper;
import com.example.demo.response.PlayerResponse;
import com.example.demo.response.PlayersResponse;
import com.example.demo.service.PlayerService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping(path = "api/v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<PlayersResponse> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();

        if(players.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(PlayerMapper.toPlayersResponse(players));
    }

    @GetMapping("/{playerID}")
    public ResponseEntity<PlayerResponse> getPlayerByID(@PathVariable String playerID) {
        if(StringUtils.isBlank(playerID)) throw new ValidationException("Invalid PlayerID");
        Optional<Player> player = playerService.getPlayerById(playerID);

        if(player.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(PlayerMapper.toPlayerResponse(player.get()));
    }
}
