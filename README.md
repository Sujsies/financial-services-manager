# 💰 Financial Services Management System

A full-stack Spring Boot application designed for financial service businesses (Tax Consultants, Loan Agencies, Investment Firms). This system allows businesses to showcase their services and manage client enquiries efficiently with a built-in Admin Dashboard.

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-green)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)
![Thymeleaf](https://img.shields.io/badge/Frontend-Thymeleaf%20%2B%20Bootstrap-purple)

## 🚀 Features

### 🌐 Public Portal (Client Side)
* **Service Showcase:** Displays financial services with descriptions and pricing in a responsive grid.
* **Lead Generation:** "Request Callback" form for potential clients to submit inquiries.
* **Responsive Design:** Fully mobile-friendly interface using Bootstrap 5.

### 🛠 Admin Dashboard (Business Side)
* **Lead Management:** View all incoming enquiries in real-time.
* **Search Functionality:** Instantly search leads by Name or Phone Number.
* **Status Tracking:** Toggle lead status between `PENDING` 🟡 and `CONTACTED` ✅.
* **Service Management:**
    * **Add** new services with images and pricing.
    * **Edit** existing service details (Price/Description).
    * **Delete** discontinued services.

---

## 📸 Screenshots

*(Optional: Add screenshots of your actual application here)*

| Client Home Page | Admin Dashboard |
|:---:|:---:|
| ![Home](https://via.placeholder.com/400x200?text=Home+Page+Screenshot) | ![Admin](https://via.placeholder.com/400x200?text=Admin+Dashboard+Screenshot) |

---

## 🛠 Tech Stack

* **Backend:** Java, Spring Boot (Web, Data JPA)
* **Frontend:** Thymeleaf, HTML5, Bootstrap 5, FontAwesome
* **Database:** MySQL
* **Build Tool:** Maven

---

## ⚙️ Setup & Installation

### 1. Prerequisites
* Java Development Kit (JDK) 17 or 21
* MySQL Server installed and running
* Maven

### 2. Database Setup
Open your MySQL Workbench and run the following script to create the database and required tables:

```sql
CREATE DATABASE finance_db;
USE finance_db;

-- Table for Services
CREATE TABLE services (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    price_range VARCHAR(100),
    image_url VARCHAR(500)
);

-- Table for Client Leads
CREATE TABLE enquiries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_name VARCHAR(100),
    client_phone VARCHAR(20),
    service_interested VARCHAR(100),
    message TEXT,
    status VARCHAR(50) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert Dummy Data
INSERT INTO services (title, description, price_range) VALUES 
('GST Registration', 'Complete GST filing for startups', '₹1500');
