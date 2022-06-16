package com.company.service;

import com.company.dto.change.ChangePhoneDTO;
import com.company.dto.responce.ClientResponseDTO;
import com.company.entity.ClientEntity;
import com.company.enums.Roles;
import com.company.enums.Status;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.exception.PhoneAlreadyExistsException;
import com.company.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {


    private final ClientRepository clientRepository;

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

        return n > 0;
    }

    public Boolean changeStatus(ClientResponseDTO dto, String id) {
        ClientEntity entity = getById(id);

        Integer n = clientRepository.changeStatus(Status.ACTIVE, id);


        return n > 0;
    }


    public Boolean changePhone(ChangePhoneDTO dto, String id) {
        ClientEntity entity = getById(id);

        Integer n = clientRepository.changePhone(Status.ACTIVE, id);

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
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Object paginationlist(int page, int size) {
        return null;
    }

    public Object getDtoById(String id) {
        return null;
    }

    public Object delete(String id) {
        return null;
    }
}