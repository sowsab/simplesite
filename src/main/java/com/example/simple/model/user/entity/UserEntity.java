package com.example.simple.model.user.entity;

import java.util.List;

import com.example.simple.model.comment.entity.CommentEntity;
import com.example.simple.model.post.entity.PostEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`USER`")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, unique = true)
    private Long idx;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoleEntitiyList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<PostEntity>postEntityList;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<CommentEntity>commentEntitiyList;
    
}
