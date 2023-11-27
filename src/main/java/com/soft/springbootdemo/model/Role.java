package com.soft.springbootdemo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id private UUID id;
    @Column(unique = true, nullable = false) private String name;
    private LocalDateTime created;
    private LocalDateTime updated;
}
