package com.company.service;

import com.company.dto.responce.CardResponseDTO;
import com.company.entity.CardEntity;
import com.company.enums.Status;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardService {


    private final CardRepository cardRepository;


    public CardResponseDTO create(CardResponseDTO dto) {
        CardEntity entity = new CardEntity();
        entity.setBalance(dto.getBalance());
        entity.setCreateDate(dto.getCreateDate());
        entity.setNumber(dto.getNumber());
        entity.setExpiredDate(dto.getExpiredDate());

        cardRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public CardEntity getById(String id) {
        return cardRepository.findById(UUID.fromString(id)).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Not Found!");
        });
    }

    public CardEntity getByNumber(String number) {
        return cardRepository.findByNumber(number).orElseThrow(() -> {
            log.warn("Not found {}", number);
            return new ItemNotFoundException("Not Found!");
        });
    }

    public Boolean changeStatus(Status dto, String id) {

        Integer n = cardRepository.changeStatus(Status.ACTIVE, id);

        return n > 0;
    }
}