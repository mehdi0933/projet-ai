package com.formationspring.demo.aimodel;

//import com.formationspring.demo.dtoT.AiDto;
import org.example.dto.AiDto;
import org.example.emu.SupportedAi;
import java.io.IOException;

public abstract class AbstractAiSearch {

    public abstract String callApi(AiDto.PostInput postInput) throws IOException, InterruptedException;

    public abstract SupportedAi getModel();
}
