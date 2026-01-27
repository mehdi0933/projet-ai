package com.formationspring.demo.services.Interface;
import com.formationspring.demo.entity.AiRecordEntity;
import java.util.List;
import org.example.dto.AiDto;
import org.example.dto.AiDto.PostOutput;


public interface AiHistoryRecorderInterface {

    void save(AiDto.PostInput input);

    AiDto.PostOutput findPostByMail(String mail);

    List<PostOutput> findAllPostByMail(String mail);
}
