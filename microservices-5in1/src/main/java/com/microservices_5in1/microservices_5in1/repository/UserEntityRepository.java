package com.microservices_5in1.microservices_5in1.repository;

import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
}
