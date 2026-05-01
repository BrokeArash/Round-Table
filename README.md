# Round Table

## Overview

Round Table is a terminal-based two-player strategy game written in Java.  
The project focuses on Object-Oriented Programming principles, clean architecture, and game system design.

Players create accounts, log into the game, and battle each other using knight characters inspired by medieval fantasy themes. The project is designed using the MVC (Model-View-Controller) architecture to keep the code modular, maintainable, and easy to expand.

This project was mainly created as a learning experience for advanced Java programming and software architecture.

---

# Features

## User System

- Register new accounts
- Login and authentication system
- Persistent user data storage

## Gameplay

- Two-player turn-based battles
- Knight-based combat mechanics
- Character stats and abilities
- Strategy-focused gameplay
- Terminal/CLI interface

## Architecture

- Fully object-oriented design
- MVC architecture implementation
- Modular and expandable structure
- Separation of game logic, views, and controllers

---

# Technologies Used

- **Java**
- **Object-Oriented Programming**
- **MVC Architecture**
- Terminal / CLI Interface

---

# Project Structure

```text
src/
│
├── model/        # Game data and core logic
├── view/         # Terminal interface and menus
├── controller/   # Input handling and game flow
└── Main.java     # Entry point of the application
```

---

# OOP Concepts Used

This project makes heavy use of Object-Oriented Programming concepts such as:

## Inheritance

Used for creating different types of characters and game entities.

## Encapsulation

Game data and player information are protected inside classes with controlled access.

## Polymorphism

Different entities can behave differently while sharing common interfaces or parent classes.

## Abstraction

Complex systems are separated into simpler interfaces and reusable components.

---

# MVC Architecture

The project follows the MVC design pattern:

## Model

Contains:
- Game logic
- Player data
- Character stats
- Save/load systems

## View

Handles:
- Terminal menus
- User interface
- Printing game information

## Controller

Responsible for:
- Managing game flow
- Handling user input
- Connecting model and view

---

# How to Run

## Clone the Repository

```bash
git clone https://github.com/BrokeArash/Round-Table.git
```

## Open the Project

Open the project using any Java IDE such as:
- IntelliJ IDEA
- Eclipse
- VS Code

## Run the Game

Compile and run the main class:

```bash
Main.java
```

---

# Goals of the Project

The purpose of this project is to practice and improve:

- Java programming skills
- Software architecture design
- OOP principles
- MVC implementation
- Game system development
- Clean and maintainable code structure

---

# License

This project is developed for educational and learning purposes.