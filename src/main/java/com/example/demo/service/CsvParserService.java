package com.example.demo.service;

import com.example.demo.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvParserService {
    List<Player> parseToDataBase(InputStream inputStream) throws IOException;
}
