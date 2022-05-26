package com.company.service;

import com.company.dto.response.ClientResponseDTO;
import com.company.dto.update.UpdateClientPhoneDTO;
import com.company.dto.update.UpdateClientStatusDTO;
import com.company.entity.ClientEntity;
import com.company.enums.ClientStatus;
import com.company.enums.Roles;
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

    public ClientResponseDTO create(ClientResponseDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhone());
        entity.setProfileName(Roles.BANK);
        entity.setCreatedDate(dto.getCreatedDate());

        clientRepository.save(entity);
        dto.setId(entity.getUuid());
        return dto;
    }

    public ClientEntity getById(String id) {
        return clientRepository.findById(UUID.fromString(id)).orElseThrow(() -> {
            log.warn("Not found {}", id);
            return new ItemNotFoundException("Not Found!");
        });
    }


    public ClientResponseDTO getByClientId(String uuid, String profileName) {
        var entity = clientRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new ItemNotFoundException("client not found"));

        if (!profileName.equals(Roles.ADMIN) || !clientRepository.findByProfileName(profileName)
                .orElseThrow().getProfileName().equals(profileName))
            throw new AppBadRequestException("not authorized!");

        return toDTO(entity);
    }

    public Boolean update(ClientResponseDTO dto, String phone) {
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

        if (!entity.getStatus().equals(dto.getOldValue()) && !entity.getProfileName().equals(Roles.BANK)) {
            throw new AppBadRequestException(".............");
        }

        String status = dto.getNewValue();
        entity.setStatus(ClientStatus.valueOf(status));

        return n > 0;
    }


    public Boolean changePhone(UpdateClientPhoneDTO dto, String id) {
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

    public PageImpl<ClientResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<ClientResponseDTO> dtoList = new ArrayList<>();

        Page<ClientEntity> entityPage = clientRepository.findAll(pageable);
        entityPage.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }

    public ClientResponseDTO toDTO(ClientEntity entity) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(entity.getUuid());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setStatus(String.valueOf(entity.getStatus()));
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
