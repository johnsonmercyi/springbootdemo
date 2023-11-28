package com.soft.springbootdemo.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  private UUID id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(unique = true, nullable = false)
  private String email;

  private String status;

  @CreationTimestamp
  private LocalDateTime created;
  
  @UpdateTimestamp
  private LocalDateTime updated;

  @OneToMany(mappedBy = "user", orphanRemoval=true)
  private Collection<UserRole> userRoles;
}
