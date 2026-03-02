# Random Maze Generator

A robust, interactive desktop application built with JavaFX that demonstrates advanced algorithmic pathfinding and maze generation. This project was developed to showcase clean object-oriented design, multi-threading, and dynamic UI rendering.

## 🚀 Key Features

- **Algorithmic Generation:** Implements efficient maze generation logic to ensure unique, solvable patterns.
- **Interactive Gameplay:** Navigate through the maze using keyboard controls with real-time collision detection.
- **Multithreaded Execution:** Utilizes Java concurrency for smooth performance during complex generation and player movements.
- **Dynamic UI:** A responsive JavaFX interface featuring custom pixel-art styling and intuitive navigation.
- **High Score Tracking:** Persistent tracking of player performance across sessions.

## 🛠 Tech Stack

- **Language:** Java 21
- **UI Framework:** JavaFX 21
- **Build Tool:** Maven
- **Testing:** JUnit 5
- **Resources:** Custom TrueType Fonts (Pixelify Sans)

## 🏗 Architecture Overview

The project follows a modular architecture to ensure maintainability and scalability:
- **`com.example.javamazegenerator`**: Core application logic and GUI screen management (`HomeScreen`, `MazeScreen`).
- **`com.example.javamazegenerator.player`**: Encapsulates player state and movement logic, including `RunnablePlayer` for threaded execution.
- **Modular Design**: Leveraging `module-info.java` for strict encapsulation and optimized runtime.

## 🚦 Getting Started

### Prerequisites
- Java Development Kit (JDK) 21 or higher.
- Maven 3.6 or higher.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/vishwatejD/Random-Maze-Generator.git
   cd Random-Maze-Generator
   ```

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw javafx:run
   ```

## 🧪 Testing
The project includes a suite of unit tests to ensure algorithmic correctness.
```bash
./mvnw test
```

## 📝 License
Distributed under the MIT License. See `LICENSE` for more information.
