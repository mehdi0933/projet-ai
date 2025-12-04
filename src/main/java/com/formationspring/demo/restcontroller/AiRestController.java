package com.formationspring.demo.restcontroller;

import com.formationspring.demo.services.AiHistoryRecorderHistoryService;
import com.formationspring.demo.services.AiRequestHandlerService;
import org.example.dto.AiDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.RedirectView;

@RestController
@RequestMapping("/ai")
public class AiRestController {

    private AiRequestHandlerService aiRequestHandlerService;
    private AiHistoryRecorderHistoryService aiHistoryRecorderService;

    public AiRestController(AiRequestHandlerService aiRequestHandlerService, AiHistoryRecorderHistoryService aiHistoryRecorderService) {
        this.aiRequestHandlerService = aiRequestHandlerService;
        this.aiHistoryRecorderService = aiHistoryRecorderService;
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello depuis IA !";
    }

    @GetMapping("/id/{mail}")
    public String findByIdRequest(@PathVariable String mail) {
        AiDto.PostOutput searchResultById = aiHistoryRecorderService.findPostByMail(mail);
        return searchResultById != null ? searchResultById.toString() : "Aucun résultat trouvé pour id=" + mail;
    }

    @PostMapping("/search")
    public String search(@RequestBody AiDto.PostInput input) throws Exception {
        return aiRequestHandlerService.sendAiAPIRequest(input);
    }


}


/**
 {
 "promptMsg": "Quelle est la capitale de la USA ?",
 "apiKey": "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",
 "url": "https://openrouter.ai/api/v1/chat/completions",
 "model": "MISTRAL"
 }
 {"id":"gen-1753406093-i297oXIqHVrg1Wn69nBS","provider":"DeepInfra","model":"mistralai/mistral-7b-instruct:free","object":"chat.completion","created":1753406093,"choices":[{"logprobs":null,"finish_reason":"stop","native_finish_reason":"stop","index":0,
 "message":{"role":"assistant","content":"
 La capitale des États-Unis d'Amérique est Washington D.C.
 En effet, Washington D.C. n'est pas un État des États-Unis, mais une entité indépendante uniquement consacrée à être la capitale fédérale du pays. C'est ici que réside le Congrès des États-Unis et le gouvernement fédéral américain, ainsi que de nombreux ministères, institutions et monuments de renom.\n\nLe mot \"quelle\" indique que la demande pose une question sur la nature d'une chose, dans ce cas-ci, un pays (la capitale). C'est pourquoi j'ai donné la réponse sans ajouter de \"ville\" à la fin, étant donné que Washington D.C. est déjà une ville.","refusal":null,"reasoning":null}}],"usage":{"prompt_tokens":15,"completion_tokens":183,"total_tokens":198,"prompt_tokens_details":null}}

 */