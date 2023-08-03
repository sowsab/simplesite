package com.example.simple.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.simple.model.user.entity.UserEntity;
import java.util.Optional;



public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByIdx(Long idx);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(String id);

}
