package com.formationspring.demo.services.Interface;

import com.formationspring.demo.dto.ArticleDto;

public interface ArticleInterface {

    ArticleDto.Output findPostById(int id);

    ArticleDto.Output createPost(ArticleDto.Input articleDtoInput);
}
