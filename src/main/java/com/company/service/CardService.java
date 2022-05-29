package com.company.service;

import com.company.entity.CardEntity;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.enums.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private Logger log = LoggerFactory.getLogger(CardService.class);

    public CardResponseDTO create(CardResponseDTO dto) {
        CardEntity entity = new CardEntity();
        entity.setBalance(dto.getBalance());
        entity.setCreateDate(dto.getCreateDate());
        entity.setNumber(dto.getNumber());
        entity.setExpiredDate(dto.getExpiredDate());

        cardRepository.save(entity);
        dto.setId(entity.getUuid());
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

    public Boolean changeStatus(ChangeStatusCardDTO dto, String id) {
        CardEntity entity = getById(id);

        Integer n = cardRepository.changeStatus(CardStatus.ACTIVE, id);

        if (!entity.getStatus().equals(dto.getOldValue())) {
            throw new AppBadRequestException(".........");
        }

        String status = dto.getNewValue();
        entity.setStatus(CardStatus.valueOf(status));

        return n > 0;
    }

}
