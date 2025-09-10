package com.formationspring.demo.services;

import com.formationspring.demo.aimodel.AbstractAiSearch;
import com.formationspring.demo.dto.AiDto;
import com.formationspring.demo.enums.ResponseType;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import com.formationspring.demo.tools.AiSearchFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AiRequestHandlerService {

    private final AiSearchFactory aiSearchFactory;
    private final CacheManager cacheManager;
    private final AiHistoryRecorderInterface llmAi;

    private static final Logger logger = LoggerFactory.getLogger(AiRequestHandlerService.class);

    public AiRequestHandlerService(AiSearchFactory aiSearchFactory, CacheManager cacheManager, AiHistoryRecorderInterface llmAi) {
        this.aiSearchFactory = aiSearchFactory;
        this.cacheManager = cacheManager;
        this.llmAi = llmAi;
    }

    public String sendAiAPIRequest(AiDto.PostInput input) throws IOException, InterruptedException {
        LocalDateTime now = LocalDateTime.now();

        AbstractAiSearch aiSearch = aiSearchFactory.getAiSearch(input.getModel());

        String cacheKey = input.getPromptMsg();
        Cache cache = cacheManager.getCache("aiResponse");

        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(cacheKey);
            if (wrapper != null) {
                String cachedValue = (String) wrapper.get();
                logger.info("Cache hit for prompt: {}", cacheKey);
                logger.info("Cached value: {} date de la requête {}", cachedValue, now);

                AiDto.PostInput cacheInput = createPostInput(input, ResponseType.CACHE, 0L, now);
                llmAi.save(cacheInput);

                return cachedValue;
            }
        }

        logger.info("No cache found for prompt: {}", input.getPromptMsg());
        long start = System.currentTimeMillis();
        String response = aiSearch.callApi(input);
        long end = System.currentTimeMillis();
        long duration = end - start;

        if (cache != null) {
            cache.put(cacheKey, response);
        }

        AiDto.PostInput aiInput = createPostInput(input, ResponseType.AI, duration, now);
        llmAi.save(aiInput);

        logger.info("Durée de la requête API : {} ms, date de la requête {}", duration, now);

        return response;
    }

    private AiDto.PostInput createPostInput(AiDto.PostInput original, ResponseType responseType, long durationMs, LocalDateTime searchDateTime) {
        return new AiDto.PostInput(
                original.getPromptMsg(),
                original.getApiKey(),
                original.getUrl(),
                original.getModel(),
                responseType,
                durationMs,
                searchDateTime
        );
    }

    @Scheduled(cron = "0 0 3 * * *") // purge cache à 3h du matin
    @CacheEvict(value = "aiResponse", allEntries = true)
    public void clearCache() {
        logger.info("Cache vidé à 3h du matin");
    }

    public Object getFromDataCache() {
        Cache cache = cacheManager.getCache("aiResponse");

        if (cache instanceof ConcurrentMapCache concurrentMapCache) {
            return concurrentMapCache.getNativeCache();
        }

        return Map.of();
    }
}