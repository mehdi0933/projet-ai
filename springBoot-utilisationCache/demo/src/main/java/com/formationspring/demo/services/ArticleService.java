package com.formationspring.demo.services;

import com.formationspring.demo.dto.ArticleDto;
import com.formationspring.demo.entity.ArticleEntity;
import com.formationspring.demo.services.Interface.ArticleInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArticleService implements ArticleInterface {

    private final WebClient webClient;

    public ArticleService(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public ArticleDto.Output findPostById(int id) {
        ArticleEntity entity = webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(ArticleEntity.class)
                .block();

        return ArticleDto.Output.builder()
                .userId(entity.getUserId())
                .id(entity.getId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .build();
    }

    @Override
    public ArticleDto.Output createPost(ArticleDto articleDto) {
        return null;
    }

    @Override
    public ArticleDto.Output createPost(ArticleDto.Output articleDtoOutput) {
        ArticleEntity created = webClient.post()
                .uri("/posts")
                .bodyValue(articleDtoOutput)
                .retrieve()
                .bodyToMono(ArticleEntity.class)
                .block();

        return ArticleDto.Output.builder()
                .userId(created.getUserId())
                .id(created.getId())
                .title(created.getTitle())
                .body(created.getBody())
                .build();
    }
}
