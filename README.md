# Project SuperPrice

SuperPrice is a comprehensive application designed to revolutionize the shopping experience. By comparing prices, viewing product details, and exploring various offers, users can make informed decisions and save money. This repository contains both the frontend and backend components of the SuperPrice application.

## Table of Contents

- [Group Information](#group-information)
- [Features](#features)
- [Setup and Installation](#setup-and-installation)
  - [Prerequisites](#prerequisites)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [Deployment](#deployment)
  - [Docker](#docker)
  - [Amazon ECR](#amazon-ecr)
- [Architecture Overview](#architecture-overview)
  - [Key Components](#key-components)
  - [Architecture Diagram](#architecture-diagram)

# Group Information

## Group-P02-07

## Members
* Vidyut Venkatesan (s3925040)
* Abhijeet Kumar (s3905291)
* Ibrahim Al-Ashhab (s3953973)
* Rashik Raj (s3931830)
* Udit Pradeep Malshe (s3933905)

## Code Documentation - Release 1.0.0 - 8 October 2023

### Features:

- **Price Comparison**: Allows users to compare prices of products across different stores.
- **Product Details**: Provides detailed information about products, including images, descriptions, and reviews.
- **Offer Exploration**: Showcases various offers and discounts available in different stores.
- **User Reviews**: Users can read reviews for products.
- **Search Functionality**: Enables users to search for specific products or categories.
- **User Accounts**: Allows users to create and manage their personal accounts, including profile settings and order history.
- **Cart Management**: Users can add products to their cart, view cart contents, and proceed to checkout.
- **Notification System**: Notifies users about price drops, new offers, and stock availability.
- **Multiple Payment Options**: Supports various payment methods including credit cards, digital wallets, and cash on delivery.
- **Secure Checkout**: Implements advanced security measures to ensure safe and secure transactions.

## Setup and Installation

### Prerequisites

- Java (Version 17.0 or higher)
- Node.js and npm
- Apache Maven
- IDE (e.g., Visual Studio Code, IntelliJ IDEA)
- Docker (for containerization)

## Run Instructions

### Backend-setup:

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```

2. Create a `.env` file following the template in `.env.development` file

3. Package and run the backend application:
   ```bash
   ./mvnw package && java -jar target/superprice-1.0.0-SNAPSHOT.jar
   ```

For detailed instructions on setting up and running the backend, refer to the backend README:

[Backend README](./backend/README.md)

### Frontend-setup:

1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install the necessary dependencies:
   ```bash
   npm install
   ```

3. Start the frontend application:
   ```bash
   npm start
   ```

4. Access the application:
   Open your browser and navigate to `http://localhost:3000` to access the SuperPrice application. The frontend will communicate with the backend to fetch and display data from the database.

For detailed instructions on setting up and running the frontend, refer to the frontend README:

[Frontend README](./frontend/README.md)

## Deployment

### Docker

1. Build the Docker image:
   ```bash
   docker-compose build
   ```

2. Run the Docker container:
   ```bash
   docker-compose up
   ```

### Amazon ECR

1. Authenticate Docker to ECR:
   ```bash
   aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 490946549162.dkr.ecr.us-east-1.amazonaws.com
   ```

2. Build, tag, and push the Docker image:
   ```bash
   docker build -t backend:latest ./backend/Dockerfile
   docker tag backend:latest 490946549162.dkr.ecr.us-east-1.amazonaws.com/backend:latest
   docker push 490946549162.dkr.ecr.us-east-1.amazonaws.com/backend:latest
   ```

   ```bash
   docker build -t frontend:latest ./frontend/Dockerfile
   docker tag frontend:latest 490946549162.dkr.ecr.us-east-1.amazonaws.com/frontend:latest
   docker push 490946549162.dkr.ecr.us-east-1.amazonaws.com/frontend:latest
   ```

## Architecture Overview

SuperPrice, as a comprehensive shopping comparison application, leverages a modern technology stack to ensure scalability, security, and a seamless user experience. The architecture is designed to be modular, scalable, and cloud-native, ensuring that the application can handle a large number of users and can be easily extended in the future.

## Key Components:

### **Docker**:
Docker is used to containerize both the frontend and backend components of the application. This ensures that the application runs consistently across different environments, from local development machines to production servers. Docker containers encapsulate the application and its dependencies, making deployment and scaling more straightforward.

### **Spring Boot**:
The backend of SuperPrice is built using Spring Boot, a robust framework for creating stand-alone, production-grade applications. Spring Boot simplifies the process of building production-ready applications by providing essential functionalities like security, data access, and web development out-of-the-box.

### **React**:
The frontend is developed using React, a popular JavaScript library for building user interfaces. React allows for the creation of reusable UI components, ensuring a dynamic and responsive user experience.

### **JSON Web Tokens (JWT)**:
For authentication and authorization, SuperPrice uses JWT. When a user logs in, they receive a token, which they must send with their subsequent requests. This token is verified at the backend to ensure the user is authenticated and has the necessary permissions.

### **AWS ECR**:
Amazon Elastic Container Registry (ECR) is used to store Docker container images. It's a fully managed docker container registry that makes it easy for developers to store, manage, and deploy Docker container images.

### **AWS Beanstalk**:
AWS Elastic Beanstalk is a Platform-as-a-Service (PaaS) used to deploy, manage, and scale the SuperPrice application. It abstracts infrastructure management tasks such as provisioning, scaling, and application health monitoring.

### **AWS Elastic IP**:
To ensure that the application has a static IP address, AWS Elastic IP is used. This static IP is beneficial for DNS configurations and for whitelisting IP addresses in third-party services.

## Architecture Diagram:

```
+-------------------+     +-------------------+     +-------------------+
|                   |     |                   |     |                   |
|      User's       |     |       React       |     |    Spring Boot    |
|     Browser       +----->   Frontend (web)  +----->      Backend      |
|                   |     |   (Port: 3000)    |     |   (API - Port:    |
|                   |     |                   |     |    8080)          |
+---------+---------+     +---------+---------+     +---------+---------+
          |                       |                       |
          |                       |                       |
          |                       |                       |
+---------v---------+     +-------v-------+       +-------v-------+
|                   |     |               |       |               |
|    AWS Elastic    |     |    AWS ECR    |       |    AWS ECR    |
|       IP          |     | (frontend     |       | (backend      |
|                   |     |  image)       |       |  image)       |
+---------+---------+     +-------+-------+       +-------+-------+
          |                       |                       |
          |                       |                       |
          |                       |                       |
+---------v---------+     +-------v-------+       +-------v-------+
|                   |     |               |       |               |
|    AWS Beanstalk  |     |   Docker      |       |   Docker      |
|  (Frontend Host)  |     |  (Frontend    |       |  (Backend     |
|                   |     |   Container)  |       |   Container)  |
+---------+---------+     +-------+-------+       +-------+-------+
          |                       |                       |
          |                       |                       |
          |                       |                       |
+---------v---------+     +-------v-------+       +-------v-------+
|                   |     |               |       |               |
|    MySQL DB       |     |   Adminer     |       |    AWS RDS    |
|   (Database       |     |  (DB Admin    |       |  (Database    |
|   Container)      |     |   Interface)  |       |   Service)    |
|                   |     |               |       |               |
+-------------------+     +---------------+       +---------------+
```

In this architecture:

- Users interact with the React frontend through their browsers.
- The React frontend communicates with the Spring Boot backend.
- Both the frontend and backend are containerized using Docker and their images are stored in AWS ECR.
- The backend connects to a MySQL database, which is also containerized.
- Adminer is used as a database administration interface.
- AWS Beanstalk is used to host the frontend, while AWS RDS could be used to host the database in a production environment.
- AWS Elastic IP ensures a consistent IP address for the application.

This setup ensures a modular and scalable architecture, with each component isolated in its container, making it easy to scale and manage.

