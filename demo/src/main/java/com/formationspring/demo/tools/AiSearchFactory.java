package com.formationspring.demo.tools;

import com.formationspring.demo.aimodel.AbstractAiSearch;
import org.example.emu.SupportedAi;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AiSearchFactory {

    private final Map<SupportedAi, AbstractAiSearch> aiSearchMap = new HashMap<>();

    public AiSearchFactory(List<AbstractAiSearch> implementations) {
        for (AbstractAiSearch impl : implementations) {
            aiSearchMap.put(impl.getModel(), impl);
        }
    }

    public AbstractAiSearch getAiSearch(SupportedAi model) {
        AbstractAiSearch aiSearch = aiSearchMap.get(model);
        if (aiSearch == null) {
            throw new IllegalArgumentException("Modèle AI non supporté : " + model);
        }
        return aiSearch;
    }
}
