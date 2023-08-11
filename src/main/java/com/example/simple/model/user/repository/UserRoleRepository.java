package com.example.simple.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simple.model.user.entity.UserRoleEntity;
import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findByUserEntity_idx(Long idx);
}
