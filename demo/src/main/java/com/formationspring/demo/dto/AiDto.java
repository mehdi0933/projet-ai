/**package com.formationspring.demo.dtoT;

import org.example.emu.SupportedAi;
import com.formationspring.demo.enums.ResponseType;

import lombok.*;
import java.time.LocalDateTime;

@Data
public class AiDtoT {

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostInput {
        private String promptMsg;
        private String apiKey;
        private String url;
        private SupportedAi model;
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
        private SupportedAi model;
        private ResponseType responseType;
        private long durationMs;
        private LocalDateTime searchDateTime;
    }
}*/
