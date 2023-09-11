@echo off
if "%1" == "-j" (java -jar target/superprice-0.0.1-SNAPSHOT.jar) ^
else if "%1" == "-p" (.\mvnw package) ^
else (.\mvnw package && java -jar target/superprice-0.0.1-SNAPSHOT.jar)
