## About Maze application
> Using this small program to explain about the OOP.
<p align="center">
  <img src="https://github.com/user-attachments/assets/6a2690ad-dac1-4802-9fb3-f49cd0e73e5b" width="350">
</p>

> To run the program, please read the follow:
#### Requirements
* Java 1.17

#### Installation
```
git clone
```

#### Run 
```
run file main.Main.java
```
#### Class Diagram
```mermaid
classDiagram

%% =====================
%% Clases principales
%% =====================

class Maze {
    -Room[][] rooms
}

class Room {
    -Exit[] exits
    -Item item
    -Character character
}

class Escape {
}

class Exit {
    -Room nextRoom
}

%% =====================
%% Elementos del juego
%% =====================

class Element {
    -String image
    -Room room
}

class Character {
    -boolean alive = true
}

class Player {
    -Item inventory
}

class Monster {
    -Random movement
}

class Item {
    -String name
}

class Door {
    -Item key
    -boolean open
    -Door reverseDoor
    +Door()
}

%% =====================
%% Herencia
%% =====================

Room <|-- Escape: extends
Exit <|-- Door: extends

Element <|-- Character: extends
Character <|-- Player: extends
Character <|-- Monster: extends
Element <|-- Item: extends

%% =====================
%% Relaciones
%% =====================

Maze o-- Room : contains
Room o-- Exit : has

Room o-- Item : contains

Player o-- Item : carries
Door *-- Item : key

```
