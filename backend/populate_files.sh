#!/bin/bash

# Directories
MODEL_DIR="src/main/java/rmit/sept/superprice/model"
REPO_DIR="src/main/java/rmit/sept/superprice/repository"
SERVICE_DIR="src/main/java/rmit/sept/superprice/service"
WEB_DIR="src/main/java/rmit/sept/superprice/web"
TEST_DIR="src/test/java/rmit/sept/superprice"

# Models
declare -a MODELS=("Product" "Category" "Store" "User" "Review" "Transaction" "TransactionItem" "Notification" "CartItem" "ProductCategory" "ProductStore")

# Populate Model files
for MODEL in "${MODELS[@]}"
do
    echo "package rmit.sept.superprice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class $MODEL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO: Add other fields, constructors, getters and setters
}" > "$MODEL_DIR/$MODEL.java"
done

# Populate Repository files
for MODEL in "${MODELS[@]}"
do
    echo "package rmit.sept.superprice.repository;

import rmit.sept.superprice.model.$MODEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${MODEL}Repository extends JpaRepository<$MODEL, Long> {
}" > "$REPO_DIR/${MODEL}Repository.java"
done

# Populate Service files
for MODEL in "${MODELS[@]}"
do
    echo "package rmit.sept.superprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.sept.superprice.repository.${MODEL}Repository;

@Service
public class ${MODEL}Service {

    @Autowired
    private ${MODEL}Repository ${MODEL,,}Repository;

    // TODO: Add service methods
}" > "$SERVICE_DIR/${MODEL}Service.java"
done

# Populate Controller files
for MODEL in "${MODELS[@]}"
do
    echo "package rmit.sept.superprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmit.sept.superprice.service.${MODEL}Service;

@RestController
@RequestMapping(\"/${MODEL,,}\")
public class ${MODEL}Controller {

    @Autowired
    private ${MODEL}Service ${MODEL,,}Service;

    // TODO: Add controller methods
}" > "$WEB_DIR/${MODEL}Controller.java"
done

# Populate Test files
for MODEL in "${MODELS[@]}"
do
    echo "package rmit.sept.superprice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ${MODEL}Tests {

    @Test
    public void contextLoads() {
    }
}" > "$TEST_DIR/${MODEL}Tests.java"
done

echo "Files updated successfully!"
