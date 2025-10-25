package com.formationspring.demo.services;

import com.formationspring.demo.dto.ArticleDto;
import com.formationspring.demo.services.Interface.ArticleInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ArticleService implements ArticleInterface {

    private final WebClient webClient;

    @Override
    public ArticleDto.Output findPostById(int id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(ArticleDto.Output.class)
                .block();
    }

    @Override
    public ArticleDto.Output createPost(ArticleDto.Input articleDtoInput) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(articleDtoInput)
                .retrieve()
                .bodyToMono(ArticleDto.Output.class)
                .block();
    }
}
