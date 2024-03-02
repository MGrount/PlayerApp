package com.example.demo.controller;

import com.example.demo.entity.Player;
import com.example.demo.response.PlayerResponse;
import com.example.demo.response.PlayersResponse;
import com.example.demo.service.PlayerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {
    @InjectMocks
    private PlayerController playerController;
    @Mock
    private PlayerService playerService;

    @Test
    void getAllPlayers() {
        when(playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        ResponseEntity<PlayersResponse> response = playerController.getAllPlayers();
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    void getPlayerByID_PlayerNotFound_ReturnsNotFoundResponse() {
        String playerId = "1";
        when(playerService.getPlayerById(playerId)).thenReturn(Optional.empty());
        ResponseEntity<PlayerResponse> response = playerController.getPlayerByID(playerId);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
        verify(playerService, times(1)).getPlayerById(playerId);
    }

    @Test
    void getPlayerByID_PlayerFound_ReturnsPlayerResponse() {
        // Arrange
        String playerId = "1";
        Player player = new Player();
        player.setPlayerID(playerId);
        player.setNameFirst("John");

        when(playerService.getPlayerById(playerId)).thenReturn(Optional.of(player));

        // Act
        ResponseEntity<PlayerResponse> response = playerController.getPlayerByID(playerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(player.getPlayerID(), Objects.requireNonNull(response.getBody()).getPlayer().getPlayerID());
        assertEquals(player.getNameFirst(), response.getBody().getPlayer().getNameFirst());

        verify(playerService, times(1)).getPlayerById(playerId);
    }



}