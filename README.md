# Java Maze Generator Project

This is a personal project I built to practice my Java skills and learn more about how maze generation algorithms work. I wanted to create something interactive that wasn't just a simple console app, so I used JavaFX for the GUI.

## What this project does

Basically, it generates a random maze from scratch every time you run it. I used a depth-first search algorithm (Recursive Backtracker) to make sure every maze is solvable and doesn't have any loops. Once the maze is generated, you can actually play it! You control a player character and try to reach the goal.

## Features I worked on

*   **Maze Generation:** I implemented the maze building logic using a stack to keep track of visited cells. It's fun to watch the path carve itself out.
*   **JavaFX UI:** I spent some time making the UI look decent. I used a custom "Pixelify" font to give it a bit of a retro game feel.
*   **Player Movement:** You can move around using the keyboard. I had to figure out how to handle collision detection so the player doesn't just walk through walls.
*   **High Scores:** I added a simple system to track high scores across different games.
*   **Multi-threading:** To keep the UI from freezing while the maze is being generated, I used an `AnimationTimer` and some basic threading concepts.

## Technologies I used

*   **Java 21:** The core language for the project.
*   **JavaFX:** For all the windows, buttons, and drawing the maze.
*   **Maven:** To manage dependencies and build the project easily.
*   **JUnit 5:** I wrote some tests to make sure my logic was working right.

## How to run it

If you want to try it out, you'll need JDK 21 and Maven installed.

1.  Clone the repo:
    ```bash
    git clone https://github.com/vishwatejD/Random-Maze-Generator.git
    cd Random-Maze-Generator
    ```
2.  Build the project:
    ```bash
    ./mvnw clean install
    ```
3.  Run it:
    ```bash
    ./mvnw javafx:run
    ```

## What I learned

Working on this taught me a lot about recursion and how to manage state in a GUI application. It was also my first time really using JavaFX modules, which was a bit tricky at first but made the project much more organized.
