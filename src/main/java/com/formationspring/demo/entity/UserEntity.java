package com.formationspring.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id") // Indique à JPA que la colonne SQL s'appelle 'id'
  private Long id;    // Le champ Java s'appelle désormais 'id' au lieu de 'user_id'

  @Column(unique = true)
  private String mail;

  private String password;

  @Column(name = "searchDateTime")
  private LocalDateTime searchDateTime;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"), // Référence à la colonne 'id' de cette table
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<RoleEntity> roles;
}