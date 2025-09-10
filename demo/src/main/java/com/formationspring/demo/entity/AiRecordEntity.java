package com.formationspring.demo.entity;


import com.formationspring.demo.enums.AiModel;
import com.formationspring.demo.enums.ResponseType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "ai_data_record_ log")
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
    private AiModel model;
    @Enumerated(EnumType.STRING)
    private ResponseType responseType;
}