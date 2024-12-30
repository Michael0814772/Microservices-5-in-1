package com.microservices_5in1.microservices_5in1.repository;

import com.microservices_5in1.microservices_5in1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "DELETE FROM user_entity WHERE id = :id", nativeQuery = true)
    int deleteCount(@Param("id") int id);
}
