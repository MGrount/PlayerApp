package com.example.demo.service;

import com.example.demo.entity.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CsvParserServiceImpl implements CsvParserService {

    @Override
    public List<Player> parseToDataBase(InputStream inputStream) throws IOException {
        List<Player> players = new ArrayList<>();

        try (Reader in = new BufferedReader(new InputStreamReader(inputStream))) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build();

            try (CSVParser csvParser = csvFormat.parse(in)) {
                for (CSVRecord record : csvParser) {
                    Player player = parsePlayer(record);
                    if (player != null) {
                        players.add(player);
                    }
                }
            }
        }

        return players;
    }

    private Player parsePlayer(CSVRecord record) {
        try {
            Player player = new Player();
            player.setPlayerID(record.get("playerID"));
            player.setBirthYear(parseInteger(record.get("birthYear")));
            player.setBirthMonth(parseInteger(record.get("birthMonth")));
            player.setBirthDay(parseInteger(record.get("birthDay")));
            player.setBirthCountry(record.get("birthCountry"));
            player.setBirthState(record.get("birthState"));
            player.setBirthCity(record.get("birthCity"));
            player.setDeathYear(parseInteger(record.get("deathYear")));
            player.setDeathMonth(parseInteger(record.get("deathMonth")));
            player.setDeathDay(parseInteger(record.get("deathDay")));
            player.setDeathCountry(record.get("deathCountry"));
            player.setDeathState(record.get("deathState"));
            player.setDeathCity(record.get("deathCity"));
            player.setNameFirst(record.get("nameFirst"));
            player.setNameLast(record.get("nameLast"));
            player.setNameGiven(record.get("nameGiven"));
            player.setWeight(parseInteger(record.get("weight")));
            player.setHeight(parseInteger(record.get("height")));
            player.setBats(record.get("bats"));
            player.setThrowingHand(record.get("throws"));
            player.setDebut(parseDate(record.get("debut")));
            player.setFinalGame(parseDate(record.get("finalGame")));
            player.setRetroID(record.get("retroID"));
            player.setBbrefID(record.get("bbrefID"));
            return player;
        } catch (Exception e) {
            log.error("Failed to parse record: " + record);
            return null;
        }
    }

    private Integer parseInteger(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

    private LocalDate parseDate(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        } else {
            return LocalDate.parse(value);
        }
    }
}
