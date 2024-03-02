package com.example.demo.service;

import com.example.demo.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserServiceImplTest {
    private static final String file = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n" +
            "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01";
    private CsvParserService csvParserService;

    @BeforeEach
    void beforeEach() {
        csvParserService = new CsvParserServiceImpl();
    }

    @Test
    public void parseToDataBase() throws IOException {
        InputStream stream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8));

        List<Player> players = csvParserService.parseToDataBase(stream);

        assertNotNull(players);
        assertEquals(players.size(), 1);

        Player player = players.get(0);
        assertNull(player.getDeathDay());
        assertEquals(player.getPlayerID(), "aardsda01");
    }
}