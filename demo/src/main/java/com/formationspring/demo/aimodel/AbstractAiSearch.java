package com.formationspring.demo.aimodel;

import com.formationspring.demo.dto.AiDto;
import org.example.SupportedAi;
import java.io.IOException;

public abstract class AbstractAiSearch {

    public abstract String callApi(AiDto.PostInput postInput) throws IOException, InterruptedException;

    public abstract SupportedAi getModel();
}
