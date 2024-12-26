package com.microservices_5in1.microservices_5in1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @SequenceGenerator(
            name = "user_entity_sequence",
            sequenceName = "user_entity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_entity_sequence"
    )
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
}
