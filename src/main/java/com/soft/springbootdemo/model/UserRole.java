package com.soft.springbootdemo.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name="user_roles")
public class UserRole {

  @Id
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  @JsonManagedReference
  private Role role;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime updated;

  public UserRole() {
    this(UUID.randomUUID(), null, null, null, null);
  }

  @Override
  public String toString() {
    return "UserRole [id=" + id + ", user=" + user + ", role=" + role + ", created=" + created + ", updated=" + updated
        + "]";
  }
}
