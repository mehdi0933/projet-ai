package com.formationspring.demo.dal;

import com.formationspring.demo.entity.AiRecordEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AiRecordEntityRepository extends JpaRepository<AiRecordEntity, Long> {

  // On utilise JPQL pour être sûr de la jointure
  @Query("SELECT a FROM AiRecordEntity a WHERE a.user.mail = :email")
  List<AiRecordEntity> findAllByUser_Mail(@Param("email") String email);
}