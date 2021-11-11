package br.com.william.exception.details;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public class ExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessge;
    protected LocalDateTime timeStamps;
}
