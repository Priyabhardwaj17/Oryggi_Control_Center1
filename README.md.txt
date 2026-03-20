# 🛡️ Oryggi Control Center - Selenium Automation Suite

This repository contains the Selenium WebDriver automation suite for the **Oryggi Control Center** web application, built using Java, Maven, and TestNG.

---

## 🔧 Tech Stack

- 🖥 Java 8
- 🌐 Selenium WebDriver
- 🔍 TestNG
- 🧪 Maven
- 📩 JavaMail API (for email alerts)
- 📸 Screenshot utility on test failure
- 📊 ExtentReports (coming soon)
- 🧠 Page Object Model (POM) design

---

## ✅ Features Covered

- 🔐 Login Automation
- 🔁 Forgot Password Automation (OTP flow)
- 🔓 Logout Flow
- 🖼️ Screenshot capture on failure
- 📬 Email alert system (under integration)
- 📋 Modular POM structure for all pages

---

## 📁 Project Structure

src └── main └── java └── com.oryggi ├── base ├── pages ├── utils └── test └── java └── com.oryggi.tests

yaml
Copy
Edit

---

## 🚀 Getting Started

1. Clone the repo  
git clone https://github.com/<your-username>/Oryggi_Control_Center.git

css
Copy
Edit

2. Navigate to project directory  
cd Oryggi_Control_Center

yaml
Copy
Edit

3. Run tests using TestNG (via TestNG XML or IDE)

---

## 📷 Screenshot on Failure

- Captured automatically to `/screenshots/`
- Triggered via custom `TestListener`

---

## 📬 Email Integration

- Email alert (with screenshot + report) on failure
- SMTP-based using `JavaMail API`
- Configured inside `EmailUtils.java`

---

## 📈 Upcoming

- 📊 ExtentReports HTML integration
- 🧪 Full regression suite
- 🌐 Cross-browser support

---

## 👩‍💻 Author

**Priya Sharma**  
QA Automation Engineer | Java + Selenium + TestNG  
📧 bharadwajpriya95@gmail.com

---

> 🔒 This project is part of Oryggi Security's internal automation initiatives.