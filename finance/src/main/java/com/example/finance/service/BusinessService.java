package com.example.finance.service; // Defines the package (folder) where this file lives

// Import the Enquiry model (the "Lead" data structure)
import com.example.finance.model.Enquiry; 
// Import the ServiceOffering model (the "Product" data structure)
import com.example.finance.model.ServiceOffering; 
// Import the EnquiryRepository interface (to talk to the 'enquiries' database table)
import com.example.finance.repository.EnquiryRepository; 
// Import the ServiceRepository interface (to talk to the 'services' database table)
import com.example.finance.repository.ServiceRepository; 
// Import Autowired (tells Spring Boot to connect things automatically)
import org.springframework.beans.factory.annotation.Autowired; 
// Import Service (marks this class as business logic)
import org.springframework.stereotype.Service; 
// Import List (a tool to hold multiple items, like an array)
import java.util.List; 

@Service // Tells Spring Boot: "This class holds the business logic. Load it on startup."
public class BusinessService { // Starts the class definition

    @Autowired // Automatically injects (connects) the ServiceRepository
    private ServiceRepository serviceRepo; // Variable to access the 'services' table

    @Autowired // Automatically injects (connects) the EnquiryRepository
    private EnquiryRepository enquiryRepo; // Variable to access the 'enquiries' table

    // --- SERVICE MANAGEMENT (Products/Offerings) ---

    // Method to get a list of all services from the database
    public List<ServiceOffering> getAllServices() { 
        return serviceRepo.findAll(); // Runs "SELECT * FROM services" and returns the list
    }

    // Method to save (create or update) a service in the database
    public void saveService(ServiceOffering s) { 
        serviceRepo.save(s); // Runs "INSERT" (if new) or "UPDATE" (if exists) SQL command
    }

    // Method to delete a service by its unique ID
    public void deleteService(Long id) { 
        serviceRepo.deleteById(id); // Runs "DELETE FROM services WHERE id = X"
    }

    // NEW: Method to find a single service by ID (Used for the Edit feature)
    public ServiceOffering getServiceById(Long id) { 
        // Tries to find the service. If not found, it returns "null" (empty).
        return serviceRepo.findById(id).orElse(null); 
    }

    // --- ENQUIRY MANAGEMENT (Client Leads) ---

    // Method to save a new client enquiry (from the contact form)
    public void saveEnquiry(Enquiry e) { 
        enquiryRepo.save(e); // Saves the client's name, phone, and message to the DB
    }

    // Method to fetch every single enquiry (for the Admin Dashboard)
    public List<Enquiry> getAllEnquiries() { 
        return enquiryRepo.findAll(); // Returns the full list of leads
    }

    // NEW: Search Logic (Finds leads by Name or Phone)
    public List<Enquiry> searchEnquiries(String keyword) { 
        // Check if the keyword is NOT null and NOT empty (avoids crashes)
        if (keyword != null && !keyword.isEmpty()) { 
            // If we have a keyword, search the DB for matching Name OR Phone
            return enquiryRepo.findByClientNameContainingIgnoreCaseOrClientPhoneContaining(keyword, keyword);
        }
        // If the search box was empty, just return the full list
        return enquiryRepo.findAll(); 
    }
} // End of the class