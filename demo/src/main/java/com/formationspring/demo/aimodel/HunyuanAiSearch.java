package com.formationspring.demo.aimodel;

//import com.formationspring.demo.dtoT.AiDtoT;
import org.example.emu.SupportedAi;
import org.example.dto.AiDto;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component("hunyuan")
public class HunyuanAiSearch extends AbstractAiSearch {

    private final AiHistoryRecorderInterface llmAi;

    public HunyuanAiSearch(AiHistoryRecorderInterface llmAi) {
        this.llmAi = llmAi;
    }

    @Override
    public SupportedAi getModel() {
        return SupportedAi.HUNYUAN;
    }

    @Override
    public String callApi(AiDto.PostInput postInput) throws IOException, InterruptedException {

        String url = postInput.getUrl();
        String apiKey = postInput.getApiKey();
        String promptMsg = postInput.getPromptMsg();

        String requestBody = """
        {
            "model": "tencent/hunyuan-a13b-instruct:free",
            "messages": [
                { "role": "user", "content": "%s" }
            ]
        }
        """.formatted(promptMsg);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey.trim())
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String aiResponse = response.body();

        System.out.println("La réponse de HUNYUAN : " + aiResponse);

        return aiResponse;
    }
}
