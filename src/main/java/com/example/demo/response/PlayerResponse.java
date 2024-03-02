package com.example.demo.response;

import com.example.demo.entity.Player;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PlayerResponse {
    private Player player;
}
