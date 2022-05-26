package com.company.service;

import com.company.dto.ClientDTO;
import com.company.dto.update.PhoneUpdateDTO;
import com.company.dto.update.UpdateClientStatusDTO;
import com.company.entity.ClientEntity;
import com.company.enums.ClientStatus;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.exception.PhoneAlreadyExistsException;
import com.company.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private Logger log = LoggerFactory.getLogger(ClientService.class);

    public ClientDTO create(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhone());
        entity.setCreatedDate(dto.getCreatedDate());

        clientRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public ClientEntity getById(String id) {
        return clientRepository.findById(UUID.fromString(id)).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Not Found!");
        });
    }

    public Boolean update(ClientDTO dto, String phone) {
        Optional<ClientEntity> optional = clientRepository.findByPhone(dto.getPhone());
        if (optional.isPresent()) {
            throw new PhoneAlreadyExistsException("Phone already exists");
        }
        Integer n = clientRepository.update(dto.getName(), dto.getSurname(), dto.getMiddleName(), phone);
        if (n > 0) {
            return true;
        }
        return false;
    }

    public Boolean changeStatus(UpdateClientStatusDTO dto, String id) {
        ClientEntity entity = getById(id);

        Integer n = clientRepository.changeStatus(ClientStatus.ACTIVE, id);

        if (!entity.getStatus().equals(dto.getOldValue())) {
            throw new AppBadRequestException("Invalid Old Password");
        }

        String status = dto.getNewValue();
        entity.setStatus(ClientStatus.valueOf(status));

        return n > 0;
    }


    public Boolean changePhone(PhoneUpdateDTO dto, String id) {
        ClientEntity entity = getById(id);

        Integer n = clientRepository.changePhone(ClientStatus.ACTIVE, id);

        if (!entity.getPhone().equals(dto.getOldValue())) {
            log.warn("Invalid Old Phone {}", id);
            throw new AppBadRequestException("Invalid Old Phone");
        }

        String phone = dto.getNewValue();
        entity.setPhone(phone);

        return n > 0;

    }

    public PageImpl<ClientDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ClientDTO> dtoList = new ArrayList<>();

        Page<ClientEntity> entityPage = clientRepository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public ClientDTO toDTO(ClientEntity entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setStatus(String.valueOf(entity.getStatus()));
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


}
