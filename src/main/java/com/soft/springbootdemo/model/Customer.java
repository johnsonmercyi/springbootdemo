package com.soft.springbootdemo.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id private UUID id;

  @OneToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="user_id", referencedColumnName="id")
  private User user;

  @Column(nullable=false) private String firstname;
  @Column(nullable=false) private String lastname;
  @Column(nullable=false) private String gender;
  private Date dob;
  private String address;
  private String nationality;
  @CreationTimestamp private LocalDateTime created;
  @UpdateTimestamp private LocalDateTime updated;
}
