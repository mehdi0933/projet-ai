package com.formationspring.demo.entity;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ApiEntity")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class ArticleEntity {

    private int id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String title;
    private String body;

}
