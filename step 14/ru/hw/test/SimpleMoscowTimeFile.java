package ru.hw.test;


import java.io.IOException;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SimpleMoscowTimeFile {
    
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "moscow_" + timestamp + ".txt";
        
        String content = "Московское время: " + 
                         now.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        
        try {
            Files.write(Paths.get(filename), content.getBytes());
            System.out.println("Файл создан: " + filename);
            System.out.println("Полный путь: " + Paths.get(filename).toAbsolutePath());
            System.out.println("Содержимое: " + content);
            
        } catch (IOException e) {
            System.err.println("Ошибка: не удалось создать файл - " + e.getMessage());
        }
    }
}
