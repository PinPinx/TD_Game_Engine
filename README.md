TD Game Engine
===================

**Game authoring environment launch:**  src/gae/gameView/Main.java
**Game launch:** src/player/gamePlayer/PlayerMain.java


The game authoring environment is the application that enables a user to build a game. The game the user builds can be launched through the game launch. This application takes the user to a select screen of the games available. 

Only "BoxHead 2.0" of the games available required addition to code; the other games were built completely within the game authoring environment. "BoxHead 2.0" was not built in the game authoring environment because it has features added to the game engine in a later stage of development, which were not integrated into the authoring environment.

Basic Design
-------------

The commit 35aa32828a60210f2a19d09c562a243e082bddf7, "GameObject " shows the main design of objects in the game. Starting from the premise of towers that shot towers that shot towers, while also including the basic features of Bloons TD, composition became the obvious solution. After all, if an inheritance hierarchy involving, for example, towers and projectiles was created, what of the objects that were both towers and projectiles? Thus, ALL game objects have the potential to "fire", move, and  "collide" with other objects.

Objects "collide" by imparting "Buffs". This "Buff" encapsulates the behavior of collisions- for example, an object could impart damage, a freeze, or a poison to among many possible buffs. The greatest example of the flexibility of this abstraction is the mind control interaction showcased in "BoxHead 2.0".


Boxhead 2.0
-------------------

Use the W, A, S, D keys to move, and the spacebar to shoot. Move over the different images on the edge of the screen to pick up a different gun. Bonus gun: the brain image on the bottom.
