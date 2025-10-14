package com.formationspring.demo.services.Interface;
import org.example.dto.AiDto;


public interface AiHistoryRecorderInterface {

    void save(AiDto.PostInput input);
    AiDto.PostOutput findPostByMail(String mail);
}
