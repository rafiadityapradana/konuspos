package com.kopnuspos.repositorys;
import com.kopnuspos.entitys.AssessmentEntity;
import com.kopnuspos.entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssessmentRepository extends JpaRepository<AssessmentEntity, UUID> {
    // You can add custom query methods if needed


}