package com.formationspring.demo.aimodel;

import com.formationspring.demo.dto.AiDto;
import com.formationspring.demo.enums.AiModel;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Component("mistral")
public class MistralAiSearch extends AbstractAiSearch {

    private final AiHistoryRecorderInterface llmAi;


    public MistralAiSearch(AiHistoryRecorderInterface llmAi) {
        this.llmAi = llmAi;
    }

    public AiModel getModel() {
        return AiModel.MISTRAL;
    }

    @Override
    @Cacheable(value = "aiResponse", key = "#input.promptMsg()")
    public String callApi(AiDto.PostInput input) throws IOException, InterruptedException {

        String apiKey = input.getApiKey();
        String promptMsg = input.getPromptMsg();
        String url = input.getUrl();
        String requestBody = """
        {
            "model": "mistralai/mistral-7b-instruct:free",
            "messages": [
                { "role": "user", "content": "%s" }
            ]
        }
        """.formatted( promptMsg);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey.trim())
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String aiResponse = response.body();


        System.out.println("La réponse de MISTRAL : " + aiResponse);

        return aiResponse + "\n" + LocalDateTime.now();
    }
}
