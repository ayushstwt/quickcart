# QuickCart

- The E-Commerce Application is built using Java and Spring Boot, with security, scalability, and ease of maintenance. The backend uses Spring Data JPA to interact with a MySQL database, making it easy to manage and store important entities such as users, products, categories, orders, and more. User authentication is handled by Auth0, providing secure and reliable means of REST APIs.

- The APIs are well-documented and easily accessible through Swagger UI, making it simple for developers to test and understand the various endpoints. Overall, this project provides secure Rest APIs to create a scalable platform for businesses to sell their products to customers.

The QuickCart E-Commerce platform is a comprehensive solution designed to provide a seamless and secure shopping experience. Key features include:

- User Notifications: After registration, users receive an email notification to confirm their account. Additionally, we are actively working on implementing monthly notifications that will be sent to users via email, SMS, or WhatsApp, ensuring they stay informed about updates, promotions, and more.

- Authentication: The platform employs robust security measures with JWT (JSON Web Token) authentication for user sessions. It also supports Google Authentication, allowing users to sign in with their Google accounts for added convenience and security.

- Payment Gateway Integration: The system is designed with future-proofing in mind, with plans to integrate a payment gateway to facilitate seamless invoicing and payment processing.

- API Documentation: Swagger UI support is integrated into the platform, providing developers with a user-friendly interface to interact with and explore the API, making it easier to understand and implement the platform’s functionalities.

- Containerization: To ensure scalability and ease of deployment, the entire application is containerized using Docker. This approach allows for consistent environments across development, testing, and production, streamlining the deployment process.

# Features
## Admin:-
- Login
- Users
- Address
- Categories
- Products
- Price & discount
- Orders

## User:-
- Registration & Login
- Fetch categories and products based on category
- Adding & deleting products to cart
- Managing address and products quantity
- Ordering products and fetching order status

# Security
- The API is secured using JSON Web Tokens (JWT) handled by Auth0. To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorize option available in the Swagger-ui.

  ### Example:
  - Authorization: <your_jwt>

# Technologies:
- Java 17 or above
- Spring Boot 3.0
- Maven
- MySQL
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)
- Auth0
- Swagger UI

# Running the app
1. Clone the repository: https://github.com/ishrivasayush/quickcart.git
2. Import the project into STS:
  - Click File > Import...
  - Select Maven > Existing Maven Projects and click Next
  - Browse to the project directory and click Finish
3. Update the values in application.properties with your MySQL database connection details.
4. Run the app: Right-click the project in the Package Explorer and click Run As > Spring Boot App.

# API documentation
- API documentation is available via Swagger UI at http://localhost:8081/swagger-ui/index.html

# ER-Diagram
<img width="605" alt="ER-Diagram" src="https://user-images.githubusercontent.com/101395494/216134703-e7cefef6-187f-44df-9fd4-52aedc66d24b.png">

# Swagger-ui
![image](https://github.com/user-attachments/assets/1467b841-129d-4fc8-b9ab-5760c49cede0)


# API Controllers
![image](https://github.com/user-attachments/assets/5cfe4e33-f620-43b2-a738-883d9f61d604)
![image](https://github.com/user-attachments/assets/621a24fe-2f7e-44e4-af6b-04563a4b1bb2)
![image](https://github.com/user-attachments/assets/2432c47d-b2f7-4aea-a8c3-af5680d359f9)
![image](https://github.com/user-attachments/assets/cec9a255-390e-47be-ab52-340ae26ddcd1)
![image](https://github.com/user-attachments/assets/919ce401-b1ee-4ab0-ba2e-5b68b06ef551)
![image](https://github.com/user-attachments/assets/97b4e1a1-2340-4807-b05b-435bef164198)
![image](https://github.com/user-attachments/assets/c493557c-b20e-4e82-a210-d899df6c9351)



# Thank You
QuickCart is built to deliver speed, security, and user satisfaction, making it an ideal choice for users seeking an efficient and reliable e-commerce platform.
