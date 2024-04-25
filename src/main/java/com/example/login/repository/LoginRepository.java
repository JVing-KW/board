package com.example.login.repository;

import com.example.login.domain.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository< LoginEntity,Long> {
}
