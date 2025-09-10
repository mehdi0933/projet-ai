package com.formationspring.demo.aimodel;

import com.formationspring.demo.dto.AiDto;
import com.formationspring.demo.enums.AiModel;

import java.io.IOException;

public abstract class AbstractAiSearch {

    public abstract String callApi(AiDto.PostInput postInput) throws IOException, InterruptedException;

    public abstract AiModel getModel();
}
