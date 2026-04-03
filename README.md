# 🧪 NopCommerce Promotions Module Automation (Batch 4)

## 📌 Project Overview

This project focuses on **automation testing of the Promotions module** in the NopCommerce application using:

* 🔹 Selenium WebDriver (UI Testing)
* 🔹 Rest Assured (API Testing)
* 🔹 TestNG (Test Execution & Assertions)

The Promotions module consists of multiple submodules, each handled by different team members.

---

## 👥 Team Details (Batch 4)

| Name     | Responsibility                |
| -------- | ----------------------------- |
| Ujjwal   | Subscription Type & Campaigns |
| Vanshaj  | Newsletter Subscribers        |
| Anushka  | Affiliates                    |
| Shraadha | Newsletter Subscribers        |

---

## 🎯 Scope of Testing

### 🔸 UI Automation (Selenium)

* Validate navigation across Promotions module
* Verify CRUD operations
* Form validations
* UI element visibility & behavior

### 🔸 API Automation (Rest Assured)

* Validate API responses
* Status code verification
* Data validation from response body
* Integration checks with UI

---

## 📂 Modules Covered

### 1. Discounts

* Create / Edit / Delete Discounts
* Apply discount rules
* Validate discount behavior

### 2. Affiliates

* Add new affiliate
* Update affiliate details
* Validate affiliate status

### 3. Newsletter Subscribers

* Add subscriber
* Search & filter subscribers
* Delete subscriber

### 4. Subscription Type ✅ *(Handled by Ujjwal)*

* Create new subscription type
* Edit existing subscription type
* Delete subscription type
* Validate required fields & error messages
* Verify data persistence

### 5. Campaigns ✅ *(Handled by Ujjwal)*

* Create campaign
* Send campaign to subscribers
* Validate campaign content
* Verify scheduling functionality
* Check campaign delivery behavior

---

## 🛠️ Tech Stack

* Java
* Selenium WebDriver
* Rest Assured
* TestNG
* Maven
* WebDriverManager

---

## 📁 Project Structure

```
Project_Batch4
│── src/test/java
│   ├── tests
│   ├── pages
│   ├── api
│   └── utils
│
│── testng.xml
│── pom.xml
│── README.md
```

---

## 🚀 How to Run the Project

### 1. Clone Repository

```bash
git clone https://github.com/teche74/Project_Batch4.git
```

### 2. Navigate to Project

```bash
cd Project_Batch4
```

### 3. Run Tests

```bash
mvn clean test
```

---

## 🔐 Test Credentials

*(Add if applicable)*

```
Username: admin@yourstore.com
Password: admin
```
## 📎 Repository Link

👉 https://github.com/teche74/Project_Batch4

---
