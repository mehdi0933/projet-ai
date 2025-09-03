package com.formationspring.demo.dto;

import com.formationspring.demo.enums.AiModel;
import com.formationspring.demo.enums.ResponseType;
import lombok.NonNull;
import java.time.LocalDateTime;

public class AiDto {

    public static record PostInput(
            @NonNull String promptMsg,
            @NonNull String apiKey,
            @NonNull String url,
            @NonNull AiModel model,
            @NonNull ResponseType responseType,
            long durationMs,
            LocalDateTime searchDateTime
    ) {}
    
    public static record PostOutput(
            String promptMsg,
            String apiKey,
            String url,
            AiModel model,
            ResponseType responseType,
            long durationMs,
            LocalDateTime searchDateTime
    ) {}

}
