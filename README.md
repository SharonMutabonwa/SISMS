# SISMS
# 🧠 Smart Inventory & Sales Management System (SISMS)

A full-featured desktop inventory and sales tracking system built in Java using Swing and MySQL.  
Designed with a clean, layered architecture to support real-time validation, audit logging, and role-based access.

---

## 🔍 Overview

SISMS is a desktop application designed for small to mid-sized businesses to:

- Manage product inventory
- Track sales and generate receipts
- Support multiple user roles (admin, clerk)
- Log all key actions with timestamps for accountability

The system was built with clean separation of concerns (DAO, Service, UI), mimicking the structure of a production-ready backend system.

---

## 💡 Key Features

✅ **Product Management**  
- Add, update, delete products  
- Quantity tracking and real-time stock validation  
- Product search and filtering

✅ **Sales Management**  
- Record new sales with auto-calculated totals  
- Real-time inventory deduction  
- Generate and export receipts

✅ **User Roles**  
- Role-based access control (Admin / Clerk)  
- Admins can manage inventory, users, and view reports  
- Clerks can record sales only

✅ **Audit Logging**  
- Tracks who added, updated, or deleted data  
- Timestamped logs for traceability

✅ **Receipt System**  
- Generates receipts with unique invoice IDs  
- Exports as PDF or text file (e.g., `INV-20250628-001.txt`)

---

## 🧱 Tech Stack

- **Language:** Java  
- **GUI:** Swing  
- **Database:** MySQL  
- **Architecture:** DAO → Service → UI  
- **Build Tool:** IntelliJ / NetBeans  
- **Extras:** PDF Export (iText), Logging Utility

---

## 🏗️ Project Structure

src/
│
├── model/ # Domain models (Product, Sale, User)
├── dao/ # Database operations (ProductDAO, SalesDAO)
├── service/ # Business logic (ProductService, ReportService)
├── ui/ # Java Swing UI Panels
├── util/ # Logging, PDF export, session management
└── main/ # Entry point and UI initialization

---

## 🚀 How to Run

1. Clone the repo  
2. Set up the MySQL database using the provided SQL schema  
3. Update DB credentials in `DBConnection.java`  
4. Build & run the project using IntelliJ or NetBeans

---

## 📂 Sample Data

Use the included SQL file to populate sample products, users, and sales.  
Sample login credentials:

Admin: username: admin password: admin123
Clerk: username: clerk password: clerk123


---

## 📈 Future Improvements

- Add charts for sales trends  
- Integrate barcode scanner support  
- Add support for offline/online sync  
- REST API version using Spring Boot

---

## 👨‍💻 Author

**Sharon Mutabonwa**  
Software Engineering   
Email: [mutaron37@gmail.com](mailto:mutaron37@gmail.com)  
GitHub: [SharonMutabonwa](https://github.com/SharonMutabonwa)

---

## 📜 License

This project is open-source and for educational/demo purposes.
