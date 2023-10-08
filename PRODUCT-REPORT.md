# PRODUCT REPORT

## Vision Statement

In today's fast-paced world, consumers are constantly seeking ways to make their shopping experiences more efficient, cost-effective, and enjoyable. SuperPrice emerges as a beacon in this landscape, aiming to revolutionize the way people shop. By offering a platform that seamlessly compares prices, showcases product details, and highlights various offers, SuperPrice ensures that users are always making informed decisions. The value of SuperPrice lies not just in its ability to save money for its users, but also in the time it saves and the convenience it offers. In essence, SuperPrice is not just another shopping platform; it's a comprehensive shopping assistant, ensuring that every purchase is a smart purchase.

## System Architecture

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

## Refactoring Report

During the development process, we identified several code "smells" that indicated potential areas for improvement:

1. **Duplicate Code**: We noticed repetitive code in the product listing and user management modules. This was refactored using the Template Method Pattern, allowing us to eliminate redundancy and streamline the codebase.

2. **Long Method**: Some methods, especially in the backend services, were doing too much. We broke them down into smaller, more focused methods, improving readability and maintainability.

3. **Feature Envy**: Some classes were overly interested in the data and behavior of other classes. We refactored this by moving the relevant methods to the class where the data resides, using the Move Method refactoring technique.

The primary purpose of our refactoring was to improve code readability, reduce technical debt, and ensure that our application was scalable and maintainable.

## Git Organization

Our team used a feature-branch workflow. Each new feature or bugfix was developed in a separate branch, which was then merged into the main branch upon completion and review. Commits were made frequently, with clear and descriptive commit messages. On average, there were 5-7 commits per day, ensuring that our work was consistently backed up and documented.

## Scrum Process

Our team met thrice a week. Ibrahim Al-Ashhab served as the Scrum Master. During our meetings, we discussed progress, addressed any blockers, and planned for the next sprint. Our sprints lasted one week, and at the end of each sprint, we reviewed our work and planned for the next one.

## Deployment Pipeline

Our CI/CD pipeline consisted of the following steps:

1. **Code Commit**: Developers commit code to the GitHub repository.
2. **Automated Testing**: On every commit, automated tests are run to ensure code quality and functionality.
3. **Build**: If tests pass, the code is built into a Docker container.
4. **Push to ECR**: The Docker container is then pushed to AWS ECR.
5. **Deploy to Beanstalk**: Finally, the container is deployed to AWS Beanstalk for production.

**Tools Used**:
- GitHub Actions for CI/CD
- Docker for containerization
- AWS ECR for container storage
- AWS Beanstalk for deployment

In conclusion, our team followed best practices in software development, from architecture design to deployment. We leveraged modern tools and methodologies to ensure that SuperPrice was not only functional but also scalable and maintainable.
