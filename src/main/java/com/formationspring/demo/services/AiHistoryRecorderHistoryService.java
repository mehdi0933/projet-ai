package com.formationspring.demo.services;

import com.formationspring.demo.dal.AiRecordEntityRepository;
import com.formationspring.demo.entity.AiRecordEntity;
import com.formationspring.demo.entity.UserEntity;
import com.formationspring.demo.services.Interface.AiHistoryRecorderInterface;
import com.formationspring.demo.services.Interface.UserInterface;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.example.dto.AiDto;
import org.example.dto.AiDto.PostOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiHistoryRecorderHistoryService implements AiHistoryRecorderInterface {

  private final AiRecordEntityRepository aiRecordEntityRepository;
  private final UserInterface userInterface;
  /**
   * Méthode de lecture de l'historique par email.
   * Renvoie toutes les entrées liées à un utilisateur.
   */
  @Override
  public List<PostOutput> findAllPostByMail(String mail) {
    System.out.println("DEBUG: Recherche de l'historique pour mail = [" + mail + "]");

    // Récupère uniquement les enregistrements liés à cet utilisateur
    List<AiRecordEntity> entities = aiRecordEntityRepository.findAllByUser_Mail(mail);

    System.out.println("DEBUG: Nombre d'entités trouvées = " + entities.size());

    // Transforme les entités en DTO pour l'API
    return entities.stream()
        .map(entity -> PostOutput.builder()
            .id(entity.getId())
            .promptMsg(entity.getPromptMsg())
            .apiKey(entity.getApiKey())
            .url(entity.getUrl())
            .model(entity.getModel())
            .responseType(entity.getResponseType())
            .durationMs(entity.getDurationMs())
            .searchDateTime(entity.getSearchDateTime())
            .user(entity.getUser() != null ? entity.getUser().getMail() : "Utilisateur inconnu")
            .build())
        .toList();
  }

  /**
   * Récupère le dernier enregistrement pour un email.
   */
  @Override
  public PostOutput findPostByMail(String mail) {
    List<AiRecordEntity> entities = aiRecordEntityRepository.findAllByUser_Mail(mail);
    if (entities.isEmpty()) return null;

    AiRecordEntity entity = entities.get(entities.size() - 1);
    return PostOutput.builder()
        .id(entity.getId())
        .promptMsg(entity.getPromptMsg())
        .apiKey(entity.getApiKey())
        .url(entity.getUrl())
        .model(entity.getModel())
        .responseType(entity.getResponseType())
        .durationMs(entity.getDurationMs())
        .searchDateTime(entity.getSearchDateTime())
        .user(entity.getUser() != null ? entity.getUser().getMail() : "Utilisateur inconnu")
        .build();
  }

  /**
   * Cette méthode est obligatoire pour compiler car elle est définie dans l'interface.
   * Comme ce service est uniquement pour la lecture, on la laisse non implémentée.
   */
  @Override
  public void save(AiDto.PostInput input) {
    UserEntity userEntity = userInterface.findByMail(input.getUser().trim().toLowerCase());
    if (userEntity == null) {
      throw new RuntimeException("Utilisateur introuvable : " + input.getUser());
    }

    AiRecordEntity entity = AiRecordEntity.builder()
        .promptMsg(input.getPromptMsg())
        .apiKey(input.getApiKey())
        .url(input.getUrl())
        .model(input.getModel())
        .responseType(input.getResponseType())
        .searchDateTime(input.getSearchDateTime() != null ? input.getSearchDateTime() : LocalDateTime.now())
        .durationMs(input.getDurationMs())
        .user(userEntity)
        .build();

    aiRecordEntityRepository.save(entity);
  }

}
