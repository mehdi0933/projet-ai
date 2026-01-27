package com.formationspring.demo.entity;

import org.example.emu.ResponseType;
import org.example.emu.SupportedAi;  //
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ai_data_record_log")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AiRecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String promptMsg;
  private String url;
  private String apiKey;
  private LocalDateTime searchDateTime;
  private long durationMs;

  @Enumerated(EnumType.STRING)
  @Column(name = "model", length = 50)
  private SupportedAi model;

  @Enumerated(EnumType.STRING)
  private ResponseType responseType;

  // La jointure pointe sur la colonne 'user_id' de la table 'ai_data_record_log'
  // qui elle-même référence la colonne 'id' de la table 'user'
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserEntity user;
}