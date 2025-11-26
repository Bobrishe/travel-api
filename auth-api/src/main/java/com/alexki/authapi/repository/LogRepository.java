package com.alexki.authapi.repository;

import com.alexki.authapi.entity.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<ProcessingLog, UUID> {
}
