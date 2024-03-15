package com.kopnuspos.repositorys;

import com.kopnuspos.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserReposotory extends JpaRepository<UserEntity, UUID> {
    @Query(value = "SELECT * FROM USERS A WHERE A.USERNAME = :username", nativeQuery = true)
    UserEntity findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM USERS A WHERE CAST(A.USER_ID AS text) = :userId", nativeQuery = true)
    UserEntity findByUserId(@Param("userId") String userId);
}
