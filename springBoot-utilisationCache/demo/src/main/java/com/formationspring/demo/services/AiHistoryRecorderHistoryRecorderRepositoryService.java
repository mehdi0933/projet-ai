package com.formationspring.demo.services;

import com.formationspring.demo.dal.AiRecordEntityRepository;
import com.formationspring.demo.dto.AiDto;
import com.formationspring.demo.entity.AiRecordEntity;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Builder
@Service
@RequiredArgsConstructor
public class AiHistoryRecorderHistoryRecorderRepositoryService implements AiHistoryRecorderInterface {

    private final AiRecordEntityRepository aiRecordEntityRepository;

    @Override
    public void save(AiDto.PostInput input) {
        AiRecordEntity entity = AiRecordEntity.builder()
                .promptMsg(input.promptMsg())
                .apiKey(input.apiKey())
                .url(input.url())
                .model(input.model())
                .responseType(input.responseType())
                .searchDateTime(LocalDateTime.now())
                .durationMs(input.durationMs())
                .build();

        aiRecordEntityRepository.save(entity);
    }
}

