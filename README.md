# ForoHub Spring Boot Application

## Overview
ForoHub is a Spring Boot-based application designed for secure user authentication and management, utilizing JSON Web Tokens (JWT) for authorization. The project integrates essential components for user login and secure API access, ensuring a robust and scalable solution.

## Features
- User authentication via JWT.
- Secure password encryption using BCrypt.
- Role-based access control.
- Easily configurable security filters.

## Technologies Used
- **Java 17**: Programming language.
- **Spring Boot 3.4.1**: Framework for building the application.
- **Spring Security**: For securing APIs and managing authentication.
- **Auth0 JWT**: Library for generating and validating JWTs.
- **Jakarta Servlet**: For handling HTTP requests.
- **Insomnia/Postman**: For API testing.

## Prerequisites
1. **Java 17 or higher**: Ensure Java is installed and properly configured.
2. **Maven**: For dependency management.
3. **Insomnia/Postman**: For testing the endpoints.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/forohub-spring-boot.git
   ```
2. Navigate to the project directory:
   ```bash
   cd forohub-spring-boot
   ```
3. Install dependencies:
   ```bash
   mvn install
   ```
4. Configure application properties:
   Edit the `application.properties` file and set the required properties:
   ```properties
   jwt.secret=your_secret_key
   jwt.expiration=3600000
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints
### 1. Authentication
- **POST** `/auth/login`
  - **Request Body:**
    ```json
    {
      "username": "user",
      "password": "password"
    }
    ```
  - **Response:**
    ```json
    {
      "token": "your_jwt_token"
    }
    ```

### 2. Protected Endpoints
Secure endpoints require a valid JWT token in the `Authorization` header:
- **Header:**
  ```
  Authorization: Bearer <your_token>
  ```

## How It Works
1. **User Authentication**:
   - The `/auth/login` endpoint authenticates the user and returns a JWT token.
   - Passwords are encrypted using BCrypt.
2. **JWT Validation**:
   - The `JWTAuthenticationFilter` validates incoming requests by checking the `Authorization` header for a valid token.
3. **Security Context**:
   - Upon validation, the token's subject (username) is set in the `SecurityContextHolder`.

## Testing with Insomnia
1. Send a **POST** request to `/auth/login` with valid credentials to obtain a token.
2. Use the token to access protected endpoints by including it in the `Authorization` header.

## Contributing
Contributions are welcome! Feel free to submit issues or pull requests to enhance the application.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any inquiries or support, please contact:
- Email: [your.email@example.com](mailto:your.email@example.com)
- GitHub: [yourusername](https://github.com/yourusername)

