package com.formationspring.demo.services;

import com.formationspring.demo.dal.AiRecordEntityRepository;
//import com.formationspring.demo.dtoT.AiDtoT;
import com.formationspring.demo.dto.ArticleDto;
import com.formationspring.demo.entity.ArticleEntity;
import org.example.dto.AiDto;
import com.formationspring.demo.entity.AiRecordEntity;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AiHistoryRecorderHistoryRecorderRepositoryService implements AiHistoryRecorderInterface {

    private final AiRecordEntityRepository aiRecordEntityRepository;

    @Override
    public void save(AiDto.PostInput input) {
        AiRecordEntity entity = AiRecordEntity.builder()
                .promptMsg(input.getPromptMsg())
                .apiKey(input.getApiKey())
                .url(input.getUrl())
                .model(input.getModel())
                .responseType(input.getResponseType())
                .searchDateTime(input.getSearchDateTime() != null ? input.getSearchDateTime() : LocalDateTime.now())
                .durationMs(input.getDurationMs())
                .build();

        aiRecordEntityRepository.save(entity);
    }

    public AiDto.PostOutput findPostById(Long id) {
        AiRecordEntity entity = aiRecordEntityRepository.findById(id).orElse(null);

        if (entity == null) {
            return null;
        }

        return AiDto.PostOutput.builder()
                .id(entity.getId())
                .promptMsg(entity.getPromptMsg())
                .apiKey(entity.getApiKey())
                .url(entity.getUrl())
                .model(entity.getModel())
                .responseType(entity.getResponseType())
                .durationMs(entity.getDurationMs())
                .searchDateTime(entity.getSearchDateTime())
                .build();
    }

}
