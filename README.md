# 🧪 QA Automation Challenge

This project automates a full end-to-end flow for the **Qubika Sports Club Management System**, using API + UI testing with Java, Selenium, RestAssured, and TestNG.

> ⚠️ The generated file `user-data.json` (containing test user data) is ignored via `.gitignore` for security and best practices.

---

## ✅ Implemented E2E Flow

1. Create a new user via API (`POST /api/auth/register`)
2. Validate the login page is displayed correctly
3. Log in with the created user
4. Verify successful login
5. Navigate to the **Category Types** section
6. Create a **root category**
7. Create a **sub-category** linked to the root one
8. Validate that both are displayed in the category list

---

## 🧰 Technologies Used

| Tool               | Purpose                          |
|--------------------|----------------------------------|
| Java 11            | Programming language             |
| Maven              | Dependency and build manager     |
| TestNG             | Testing framework                |
| Selenium WebDriver | UI automation                    |
| RestAssured        | API testing                      |
| WebDriverManager   | Automatic driver management      |
| JSON               | Handle API data and user payload |
| VS Code            | Compatible IDEs                  |

---

## ▶️ How to Run the E2E Test

### ✅ Requirements:
- Java 11+
- Maven installed
- Google Chrome installed

### 🛠 Build the project

`mvn clean install`

### 🧪 Run the E2E test

`mvn test -Dtest=ui.EndToEndFlowTest`
