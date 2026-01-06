package com.example.finance.repository; // Package definition

import com.example.finance.model.Enquiry; // Import the Enquiry model
import org.springframework.data.jpa.repository.JpaRepository; // Import standard repository tools
import java.util.List; // Import List tool

// Interface that extends JpaRepository to give us standard Save/Delete/Find methods
public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {

    // CUSTOM SEARCH METHOD:
    // This tells Hibernate: "Select * from Enquiries where Name contains X OR Phone contains X"
    // "IgnoreCase" means it works for "amit", "Amit", or "AMIT"
    List<Enquiry> findByClientNameContainingIgnoreCaseOrClientPhoneContaining(String name, String phone);
}