# Microblog

This is a rudimentary microblog application. It is a work in progress project built to learn and understand fundamental concepts of the Spring and Spring Boot Framework. 

Currently, it has the following features:
- View posts as a visitor,
- Create, update and delete posts as an Author,
- Cache posts using Redis,
- Store posts in MongoDB,
- Use Spring Security to authenticate users,
- Use Spring Data MongoDB to interact with MongoDB,
- Use Spring Data Redis to cache posts for faster retrieval,
- Use Spring Web to build the web application,
- Use Spring Boot as a framework, and,
- Use Gradle to build the application.

## Run inside Docker

The application can be run inside Docker. Build the application first using the command `gradlew bootJar` and create a mutli-container Docker image using the command `docker-compose up`.

## Architecture

The application is built using the Spring Boot as the framework. MongoDB is used as a database and cached using Redis. The application is containerized using Docker. 
