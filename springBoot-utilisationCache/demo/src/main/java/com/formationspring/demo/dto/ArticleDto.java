package com.formationspring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ArticleDto {


    public static  record Input (
         String title,
         String body
    ) {}


    public static record Output (
         int userId,
         int id,
         String title,
         String body

         ) {}
}
