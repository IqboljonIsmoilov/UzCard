package com.company.enums.repository;

import com.company.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    Optional<CardEntity> findById(UUID fromString);

    Optional<CardEntity> findByNumber(String number);

    Integer changeStatus(CardStatus status, String id);
}
