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
    private SupportedAi model;  // <-- utilisé depuis le module externe

    @Enumerated(EnumType.STRING)
    private ResponseType responseType;
}
