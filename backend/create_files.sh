#!/bin/bash

# Directories
MODEL_DIR="src/main/java/rmit/sept/superprice/model"
REPO_DIR="src/main/java/rmit/sept/superprice/repository"
SERVICE_DIR="src/main/java/rmit/sept/superprice/service"
WEB_DIR="src/main/java/rmit/sept/superprice/web"
TEST_DIR="src/test/java/rmit/sept/superprice"

# Models
declare -a MODELS=("Product" "Category" "Store" "User" "Review" "Transaction" "TransactionItem" "Notification" "CartItem" "ProductCategory" "ProductStore")

# Create directories if they don't exist
mkdir -p $MODEL_DIR $REPO_DIR $SERVICE_DIR $WEB_DIR $TEST_DIR

# Create files
for MODEL in "${MODELS[@]}"
do
    touch "$MODEL_DIR/$MODEL.java"
    touch "$REPO_DIR/${MODEL}Repository.java"
    touch "$SERVICE_DIR/${MODEL}Service.java"
    touch "$WEB_DIR/${MODEL}Controller.java"
    touch "$TEST_DIR/${MODEL}Tests.java"
done

echo "Files created successfully!"
