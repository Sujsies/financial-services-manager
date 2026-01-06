package com.example.finance.controller; // Package definition

// Imports for our Models and Logic
import com.example.finance.model.Enquiry;
import com.example.finance.model.ServiceOffering;
import com.example.finance.service.BusinessService;
// Imports for Spring Web tools
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller // Marks this class as a Web Controller (handles URLs)
public class WebController {

    @Autowired // Connects to the Business Logic Service
    private BusinessService businessService;

    // --- PUBLIC CLIENT SIDE ---

    @GetMapping("/") // Listens for the Home Page URL (localhost:8081/)
    public String home(Model model) {
        // Send the list of services to the HTML page
        model.addAttribute("services", businessService.getAllServices());
        // Send a blank Enquiry object for the "Contact Us" form
        model.addAttribute("newEnquiry", new Enquiry());
        return "index"; // Opens index.html
    }

    @PostMapping("/enquire") // Listens for the "Submit Form" button click
    public String submitEnquiry(@ModelAttribute Enquiry enquiry) {
        businessService.saveEnquiry(enquiry); // Saves the client's data
        return "redirect:/?success"; // Reloads home page with a success message
    }

    // --- ADMIN DASHBOARD SIDE ---

    @GetMapping("/admin") // Listens for the Admin Dashboard URL
    public String admin(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        // Create a list to hold the leads
        List<Enquiry> list;

        // CHECK: Did the user type something in the search box?
        if (keyword != null) {
            // YES: Search for that specific name/phone
            list = businessService.searchEnquiries(keyword);
        } else {
            // NO: Just show everyone
            list = businessService.getAllEnquiries();
        }
        
        // Send data to the Admin HTML page
        model.addAttribute("enquiries", list); // The list of leads (filtered or all)
        model.addAttribute("services", businessService.getAllServices()); // The list of services
        model.addAttribute("newService", new ServiceOffering()); // Empty object for "Add Service" form
        model.addAttribute("keyword", keyword); // Send the search text back (so it stays in the box)
        return "admin"; // Opens admin.html
    }

    // NEW: Handles the "Edit Service" page request
    @GetMapping("/admin/edit-service/{id}")
    public String editService(@PathVariable Long id, Model model) {
        // Fetch the specific service from DB
        ServiceOffering service = businessService.getServiceById(id);
        // Send it to the edit page
        model.addAttribute("service", service);
        return "edit_service"; // Opens edit_service.html (We need to create this file!)
    }

    @PostMapping("/admin/save-service") // Handles saving (Adding OR Updating) a service
    public String saveService(@ModelAttribute ServiceOffering service) {
        businessService.saveService(service); // Saves the service
        return "redirect:/admin"; // Go back to dashboard
    }

    @GetMapping("/admin/delete-service/{id}") // Handles the Delete button
    public String deleteService(@PathVariable Long id) {
        businessService.deleteService(id); // Deletes from DB
        return "redirect:/admin"; // Reloads dashboard
    }

    @GetMapping("/admin/toggle-status/{id}") // Handles the "Status Update" button
    public String toggleStatus(@PathVariable Long id) {
        // Find the specific enquiry
        Enquiry e = businessService.getAllEnquiries().stream()
                .filter(enq -> enq.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        // If found, flip the status
        if (e != null) {
            if ("PENDING".equals(e.getStatus())) {
                e.setStatus("CONTACTED ✅");
            } else {
                e.setStatus("PENDING");
            }
            businessService.saveEnquiry(e); // Save the new status
        }
        return "redirect:/admin"; // Reload dashboard
    }
}