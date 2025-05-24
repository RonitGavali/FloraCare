# 🌿 Flora Care Application

## ✅ 1. Introduction
Flora Care is a Spring Boot application designed for plant nurseries or gardening enthusiasts to manage their plants and log care activities like watering, fertilizing, pruning, etc. It helps maintain plant health by ensuring consistent care routines.

## 🎯 2. Objective / Problem Statement
Often in plant nurseries or homes, there’s no reliable system to track when a plant was last watered or fertilized. This can lead to overwatering, missed fertilization, or poor plant health. The goal of Flora Care is to provide a digital record for each plant and its care routine, improving maintenance and plant longevity.

## 🧰 3. Tech Stack Overview

| Layer          | Technology                           |
|----------------|--------------------------------------|
| Framework      | Spring Boot                          |
| ORM & DB       | Spring Data JPA + Hibernate, H2/MySQL|
| REST API       | Spring Web                           |
| Entities/DTO   | Clean separation using DTO pattern   |
| Logging        | Lombok @Slf4j                        |
| Error Handling | Custom exceptions and global handler |
| Testing Tools  | Postman for manual API testing       |

## 🌱 4. Core Functionalities

### 🌼 Plant Management
- Add new plants with details like name, species, price, and grow environment (indoor/outdoor/both).
- Fetch all plant details or specific plant by ID.
- Delete plant if no longer needed.

### 🧪 Care Log Management
- Add logs for each plant including care type (watering, fertilizing), notes, performedBy, etc.
- View logs for all plants or by individual ID.
- Delete logs if created by mistake.

> This two-layer data tracking (plant + care log) provides complete visibility into each plant’s maintenance history.

## 📊 5. Entity Relationships

### Entities:

#### `PlantDetail`
- Fields: `id`, `name`, `scientificName`, `species`, `growEnvironment (enum)`, `price`
- Relationship: `@OneToMany` with `PlantCareLog`

#### `PlantCareLog`
- Fields: `id`, `careType`, `wateringFrequency`, `fertilizerSchedule`, `notes`, `performedBy`
- Relationship: `@ManyToOne` with `PlantDetail` via foreign key `plant_id`

### Mapping:
```java
PlantDetail → @OneToMany(mappedBy = "plantDetail")
PlantCareLog → @ManyToOne
