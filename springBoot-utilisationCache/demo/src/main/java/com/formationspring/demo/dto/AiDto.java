package com.formationspring.demo.dto;

import com.formationspring.demo.enums.AiModel;
import com.formationspring.demo.enums.ResponseType;
import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter

public class AiDto {


    private String promptMsg;
    private String apiKey;
    private String url;
    private AiModel model;
    private ResponseType responseType;
    private LocalDateTime searchDateTime;
    private long durationMs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostInput {

        @NonNull
        private String promptMsg;

        @NonNull
        private String apiKey;

        @NonNull
        private String url;

        @NonNull
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

