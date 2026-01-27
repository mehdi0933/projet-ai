package com.formationspring.demo.model.ai;

import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import org.example.dto.AiDto;
import org.example.emu.SupportedAi;
import org.springframework.stereotype.Component;

@Component
public class FakeAi extends AbstractAiSearch {

  @Override
  public SupportedAi getModel() {
    return SupportedAi.FAKEAI;
  }
  @Override
  public String callApi(AiDto.PostInput postInput)
      throws IOException, InterruptedException {

    int delai = ThreadLocalRandom.current().nextInt(1, 6);
    Thread.sleep(delai * 1000L);

    return "fake ia";
  }

}
