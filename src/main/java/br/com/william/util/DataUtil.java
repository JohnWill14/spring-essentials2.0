package br.com.william.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataUtil {
    public String formatLocalDataTimeToDatabaseStyle(LocalDateTime ldt){
        return  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(ldt);

    }
}
