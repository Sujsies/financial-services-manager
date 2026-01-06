package com.example.finance.repository;
import com.example.finance.model.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceOffering, Long> {
}