package com.company.repository;

import com.company.entity.CardEntity;
import com.company.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    Optional<CardEntity> findById(UUID fromString);

    Optional<CardEntity> findByNumber(String number);

    Integer changeStatus(Status status, String id);
}