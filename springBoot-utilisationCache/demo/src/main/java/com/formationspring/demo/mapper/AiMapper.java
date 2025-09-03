package com.formationspring.demo.mapper;

import com.formationspring.demo.dto.AiDto;
import org.springframework.stereotype.Component;

@Component
public class AiMapper {



    public static AiDto.PostOutput fromInputToOutput(AiDto.PostInput postInput) {
        return new AiDto.PostOutput(
                postInput.promptMsg(),
                postInput.apiKey(),
                postInput.url(),
                postInput.model(),
                postInput.responseType(),
                postInput.durationMs(),
                postInput.searchDateTime()
                );
    }



    public static AiDto.PostOutput toOutput(AiDto.PostOutput PostOutput) {
        return new AiDto.PostOutput(
                PostOutput.promptMsg(),
                PostOutput.apiKey(),
                PostOutput.url(),
                PostOutput.model(),
                PostOutput.responseType(),
                PostOutput.durationMs(),
                PostOutput.searchDateTime()

        );
    }

}
