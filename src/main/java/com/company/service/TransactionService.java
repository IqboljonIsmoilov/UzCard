package com.company.service;

import com.company.dto.TransactionDTO;
import com.company.entity.CardEntity;
import com.company.entity.TransactionEntity;
import com.company.enums.Status;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CardRepository;
import com.company.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TransactionService {

    private final CardService cardService;

    private final CardRepository cardRepository;

    private final TransactionRepository transactionRepository;

    public String create(TransactionDTO dto) {
        TransactionEntity entity = new TransactionEntity();

        CardEntity fromCard = cardRepository.findByNumber(dto.getFromCard()).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        CardEntity toCard = cardRepository.findByNumber(dto.getToCard()).orElseThrow(() -> {
            throw new ItemNotFoundException("Not found");
        });
        if (fromCard.getBalance() >= dto.getAmount()) {

            fromCard.setBalance(fromCard.getBalance() - dto.getAmount());
            toCard.setBalance(toCard.getBalance() + dto.getAmount());

            entity.setFromCard(fromCard.getNumber());
            entity.setToCard(toCard.getNumber());
            entity.setAmount(dto.getAmount());
            entity.setCreatedDate(dto.getCreatedDate());
            entity.setStatus(Status.SUCCESS);
            transactionRepository.save(entity);
        } else if (fromCard.getBalance() < dto.getAmount()) {
            entity.setStatus(Status.FAILED);
            return "no Success";
        }
        return "Success";
    }


    public List<TransactionDTO> paginationListCardId(int page, int size, String cardId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();
        transactionRepository.findAllByFromCard(cardId, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });

        transactionRepository.findAllByToCard(cardId, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });
        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }


    public List<TransactionDTO> paginationListProfileName(int page, int size, String profileName) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();
        transactionRepository.findAllByProfileName(profileName, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });

        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }


    public List<TransactionDTO> paginationListClientId(int page, int size, String clientId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        List<TransactionDTO> list = new ArrayList<>();

        transactionRepository.findAllByClientId(clientId, pageable).forEach(courseEntity -> {
            list.add(toDTO(courseEntity));
        });

        if (list.isEmpty()) {
            throw new ItemNotFoundException("List bo'm bo'sh ku Mazgi");
        }
        return list;
    }


    public TransactionDTO toDTO(TransactionEntity entity) {
        TransactionDTO dto = new TransactionDTO();
        dto.setFromCard(entity.getFromCard());
        dto.setToCard(entity.getToCard());
        return dto;
    }
}