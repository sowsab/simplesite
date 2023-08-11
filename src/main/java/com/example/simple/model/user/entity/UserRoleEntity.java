package com.example.simple.model.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`USER_ROLE`")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Integer idx;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_idx", referencedColumnName = "idx", updatable = false, nullable = false)
    private UserEntity userEntity;

    @Column(name = "role")
    private String role;
    
    @Column(name = "create_date")
    private LocalDateTime createDate;

}
