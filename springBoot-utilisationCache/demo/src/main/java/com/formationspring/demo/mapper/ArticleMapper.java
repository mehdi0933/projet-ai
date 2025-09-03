package com.formationspring.demo.mapper;

import com.formationspring.demo.dto.ArticleDto;

public class ArticleMapper {

    public static ArticleDto.Output fromInputToOutput(ArticleDto.Input input) {
        return ArticleDto.Output.builder()
                .userId(0)
                .id(0)
                .title(input.getTitle())
                .body(input.getBody())
                .build();
    }

    public static ArticleDto.Output toOutput(ArticleDto.Output output) {
        return ArticleDto.Output.builder()
                .userId(output.getUserId())
                .id(output.getId())
                .title(output.getTitle())
                .body(output.getBody())
                .build();
    }
}

