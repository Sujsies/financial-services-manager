package com.example.finance.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enquiries")
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String clientPhone;
    private String serviceInterested;
    private String message;
    
    // 1. THIS IS THE NEW FIELD YOU ARE MISSING
    private String status = "PENDING"; 

    private LocalDateTime createdAt = LocalDateTime.now();

    // --- GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getClientPhone() { return clientPhone; }
    public void setClientPhone(String clientPhone) { this.clientPhone = clientPhone; }

    public String getServiceInterested() { return serviceInterested; }
    public void setServiceInterested(String serviceInterested) { this.serviceInterested = serviceInterested; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    // 2. THESE ARE THE METHODS YOUR CONTROLLER NEEDS
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}