package com.example.demo.eventListener;

import com.example.demo.entity.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.CsvParserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class CsvParseEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private final CsvParserService csvParserService;
    private final PlayerRepository playerRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ClassPathResource resource = new ClassPathResource("player.csv");
        log.info("onApplicationEvent -> pre parsing");
        try {
            List<Player> players = csvParserService.parseToDataBase(resource.getInputStream());
            playerRepository.saveAll(players);
            log.info("onApplicationEvent -> The parsing is successful!");
        } catch (IOException e) {
            log.error("onApplicationEvent -> Parsing failure " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
