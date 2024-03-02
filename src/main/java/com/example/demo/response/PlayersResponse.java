package com.example.demo.response;

import com.example.demo.entity.Player;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlayersResponse {
    private int count;
    private List<Player> players;
}
