package com.company.entity;

import com.company.enums.ClientStatus;
import com.company.enums.Roles;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "profiled_name")
    private Roles profileName;

}
