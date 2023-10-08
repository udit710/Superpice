# RMIT COSC2299 SEPT Major Project: SuperPrice

# RMIT COSC2299 SEPT Major Project

## ![SuperPrice Logo](./frontend/public/basket2-fill.svg) [SuperPrice - Price Matching and Delivery Application](http://superprice-env.eba-nd46x4ec.us-east-1.elasticbeanstalk.com)

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
- [Contribution Videos](#contribution-videos)

# Group Information

## Group-P02-07

## Members
* Vidyut Venkatesan (s3925040)
* Abhijeet Kumar (s3905291)
* Ibrahim Al-Ashhab (s3953973)
* Rashik Raj (s3931830)
* Udit Pradeep Malshe (s3933905)

## Records

* Github repository: [github.com | team-project-group-p02-07](https://github.com/cosc2299-sept-2023/team-project-group-p02-07)
* Github Project Board : [github.com | SEPT Feature Project](https://github.com/orgs/cosc2299-sept-2023/projects/143/views/1)
* Microsoft Teams Link: [MS Teams Group 07](https://teams.microsoft.com/l/team/19%3aaS2iQd5DZbipNu4gNK8p9S6Sy6-Kj2rkewpV8f8ZAqA1%40thread.tacv2/conversations?groupId=eea8373d-4f5f-4c1c-a1a7-36843caccb9c&tenantId=d1323671-cdbe-4417-b4d4-bdb24b51316b)
* Contribution video link : https://youtu.be/GzdNoQ0QKAQ



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

### Backend:

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

### Frontend:

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


## Individual contribution videos for Milestone 2
- Udit Pradeep Malshe - https://rmiteduau-my.sharepoint.com/:v:/r/personal/s3933905_student_rmit_edu_au/Documents/Udit%20Pradeep%20Malshe%20-%20s3933905%20-%20COSC2299-23s2%20PTC1.mp4?csf=1&web=1&e=1EhdyK&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZyIsInJlZmVycmFsQXBwUGxhdGZvcm0iOiJXZWIiLCJyZWZlcnJhbE1vZGUiOiJ2aWV3In19
- Abhijeet Kumar - https://rmiteduau.sharepoint.com/:v:/s/SEPT-007/EX1TX5igVItAoSV0RE_xDy4B89hKDBeKThgR0ZNhZi9RnA?e=od0kXU
- Vidyut Venkatesan - https://rmiteduau-my.sharepoint.com/:v:/g/personal/s3925040_student_rmit_edu_au/EW3XhVMMPBVLk7NGFc_x_LIBdtjIq8t0yfMOHDXEkztIfg?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZyIsInJlZmVycmFsQXBwUGxhdGZvcm0iOiJXZWIiLCJyZWZlcnJhbE1vZGUiOiJ2aWV3In19&e=CcZR5p
- Rashik Raj - https://rmiteduau-my.sharepoint.com/:v:/g/personal/s3931830_student_rmit_edu_au/ETdmMxKJO5BCq_RmJtOd0YIBPdrnXf4PoiNOhKheyMP3pw
- Ibrahim Al-Ashhab - [SEPT Milestone 2 Ibrahim-20230917_215408-Meeting Recording.mp4](https://rmiteduau.sharepoint.com/sites/solomeetingteam/_layouts/15/stream.aspx?id=%2Fsites%2Fsolomeetingteam%2FShared%20Documents%2FGeneral%2FRecordings%2FSEPT%20Milestone%202%20Ibrahim%2D20230917%5F215408%2DMeeting%20Recording%2Emp4)https://rmiteduau.sharepoint.com/sites/solomeetingteam/_layouts/15/stream.aspx?id=%2Fsites%2Fsolomeetingteam%2FShared%20Documents%2FGeneral%2FRecordings%2FSEPT%20Milestone%202%20Ibrahim%2D20230917%5F215408%2DMeeting%20Recording%2Emp4

## Individual contribution videos for Milestone 3
- Udit Pradeep Malshe - https://rmiteduau-my.sharepoint.com/:v:/r/personal/s3933905_student_rmit_edu_au/Documents/GROUP-P02-07%20COSC2299-23s2%20Milestone%203.mp4?csf=1&web=1&e=bb9cEP&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZyIsInJlZmVycmFsQXBwUGxhdGZvcm0iOiJXZWIiLCJyZWZlcnJhbE1vZGUiOiJ2aWV3In19
- Ibrahim Al-Ashhab - https://rmiteduau.sharepoint.com/sites/solomeetingteam/_layouts/15/stream.aspx?id=%2Fsites%2Fsolomeetingteam%2FShared%20Documents%2FGeneral%2FRecordings%2FGROUP%2DP2%2D07%20COSC2299%2D23s2%20Milestone%203%2Emp4
- Rashik Raj - https://rmiteduau-my.sharepoint.com/:v:/r/personal/s3931830_student_rmit_edu_au/Documents/My%20Student%20Card%20Journey.mp4?csf=1&web=1&e=aCPB37&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZyIsInJlZmVycmFsQXBwUGxhdGZvcm0iOiJXZWIiLCJyZWZlcnJhbE1vZGUiOiJ2aWV3In19
