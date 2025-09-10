package com.formationspring.demo.mapper;

import com.formationspring.demo.dto.AiDto;
import org.springframework.stereotype.Component;

@Component
public class AiMapper {

    public static AiDto.PostOutput fromInputToOutput(AiDto.PostInput postInput) {
        return AiDto.PostOutput.builder()
                .promptMsg(postInput.getPromptMsg())
                .apiKey(postInput.getApiKey())
                .url(postInput.getUrl())
                .model(postInput.getModel())
                .responseType(postInput.getResponseType())
                .durationMs(postInput.getDurationMs())
                .searchDateTime(postInput.getSearchDateTime())
                .build();
    }

    public static AiDto.PostOutput toOutput(AiDto.PostOutput postOutput) {
        return AiDto.PostOutput.builder()
                .promptMsg(postOutput.getPromptMsg())
                .apiKey(postOutput.getApiKey())
                .url(postOutput.getUrl())
                .model(postOutput.getModel())
                .responseType(postOutput.getResponseType())
                .durationMs(postOutput.getDurationMs())
                .searchDateTime(postOutput.getSearchDateTime())
                .build();
    }
}
