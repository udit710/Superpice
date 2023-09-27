FROM eclipse-temurin:17-jdk-alpine AS base

# First build the app
FROM base AS builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean package

# Now run it
FROM base AS runner
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/superprice.jar
ENTRYPOINT ["java", "-jar", "/opt/app/superprice.jar" ]
