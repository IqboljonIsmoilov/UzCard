package com.company.enums.repository;

import com.company.entity.ClientEntity;
import com.company.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    Optional<ClientEntity> findByPhone(String photo);


    @Transactional
    @Modifying
    @Query("update ClientEntity set name = :name, surname = :surname, middleName = :middleName where phone = :phone")
    Integer update(@Param("name") String name,
                   @Param("surname") String surname,
                   @Param("middleName") String middleName,
                   @Param("phone") String phone);


    @Transactional
    @Modifying
    @Query("update ClientEntity set status = :status where id = :id")
    Integer changeStatus(@Param("status") Status status,
                         @Param("id") String id);


    @Transactional
    @Modifying
    @Query("update ClientEntity  set ClientEntity.status = :status where ClientEntity.id = :pId")
    Integer changePhone(@Param("status") Status status,
                        @Param("pId") String id);


    Optional<ClientEntity> findById(UUID fromString);

    Optional<ClientEntity> findByProfileName(String profileName);
}
