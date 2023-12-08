package com.soft.springbootdemo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity
@Data
// @NoArgsConstructor
@AllArgsConstructor
public class Role {
  @Id
  private UUID id;

  @Column(unique = true, nullable = false)
  private String name;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  @OneToMany(mappedBy = "role", orphanRemoval=true)
  @JsonIgnoreProperties("role")
  private Collection<UserRole> userRoles;

  public Role() {
    this(UUID.randomUUID(), null, null, null, new ArrayList<>());
  }
}
