package com.company.repository;

import com.company.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByPhone(String photo);

    @Transactional
    @Modifying
    @Query("update ClientEntity set surname = :surname, name = :name where phone = :phone")
    Integer update(@Param("surname") String surname,
                   @Param("name") String name,
                   @Param("phone") String phone);

}
