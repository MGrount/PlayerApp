package com.example.demo.mapper;

import com.example.demo.entity.Player;
import com.example.demo.response.PlayerResponse;
import com.example.demo.response.PlayersResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class PlayerMapper {
    public PlayersResponse toPlayersResponse(List<Player> players) {
        return PlayersResponse.builder().players(players).count(players.size()).build();
    }

    public PlayerResponse toPlayerResponse(Player player) {
        return PlayerResponse.builder().player(player).build();
    }
}
