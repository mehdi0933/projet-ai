package com.formationspring.demo.mapper;

import com.formationspring.demo.dto.ArticleDto;

public class ArticleMapper {

    public static ArticleDto.Output fromInputToOutput(ArticleDto.Input input) {
        return new ArticleDto.Output(
                0,
                0,
                input.title(),
                input.body()
        );
    }

    public static ArticleDto.Output toOutput(ArticleDto.Output output) {
        return new ArticleDto.Output(
                output.userId(),
                output.id(),
                output.title(),
                output.body()
        );
    }
}
