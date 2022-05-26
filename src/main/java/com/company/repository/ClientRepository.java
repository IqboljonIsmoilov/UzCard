package com.company.repository;

import com.company.entity.ClientEntity;
import com.company.enums.ClientStatus;
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
    Integer changeStatus(@Param("status") ClientStatus status,
                         @Param("id") String id);


    @Transactional
    @Modifying
    @Query("update ClientEntity  set ClientEntity.status = :status where ClientEntity.id = :pId")
    Integer changePhone(@Param("status") ClientStatus status,
                        @Param("pId") String id);


    Optional<ClientEntity> findById(UUID fromString);
}
