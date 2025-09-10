package com.formationspring.demo.dto;

import com.formationspring.demo.enums.AiModel;
import com.formationspring.demo.enums.ResponseType;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class AiDto {

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostInput {
        private String promptMsg;
        private String apiKey;
        private String url;
        private AiModel model;
        private ResponseType responseType;
        private long durationMs;
        private LocalDateTime searchDateTime;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostOutput {
        private String promptMsg;
        private String apiKey;
        private String url;
        private AiModel model;
        private ResponseType responseType;
        private long durationMs;
        private LocalDateTime searchDateTime;
    }
}
