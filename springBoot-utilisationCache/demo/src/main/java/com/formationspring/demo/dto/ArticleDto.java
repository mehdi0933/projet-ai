package com.formationspring.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private int userId;
    private int id;
    private String title;
    private String body;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Input {
        private String title;
        private String body;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Output {
        private int userId;
        private int id;
        private String title;
        private String body;
    }
}
