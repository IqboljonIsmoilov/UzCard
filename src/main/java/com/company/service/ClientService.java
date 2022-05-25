package com.company.service;

import com.company.dto.ClientDTO;
import com.company.entity.ClientEntity;
import com.company.exception.PhoneAlreadyExistsException;
import com.company.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientDTO create(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPhone(dto.getPhoto());
        entity.setCreatedDate(dto.getCreatedDate());

        clientRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(ClientDTO dto, String phone) {
        Optional<ClientEntity> optional = clientRepository.findByPhone(dto.getPhoto());
        if (optional.isPresent()) {
            throw new PhoneAlreadyExistsException("Phone already exists");
        }
        Integer n = clientRepository.update(dto.getName(), dto.getSurname(), dto.getMiddleName(), phone);
        if (n > 0) {
            return true;
        }
        return false;
    }
}
