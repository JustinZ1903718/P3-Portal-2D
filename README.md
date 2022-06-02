# P3-Portal2D
<img width="206" alt="image" src="https://user-images.githubusercontent.com/90798634/171288703-483f8f1e-90a0-45f5-9e78-db75d5989ec7.png">
Initializing variables and classes

<img width="230" alt="image" src="https://user-images.githubusercontent.com/90798634/171289561-75bdd17f-739a-4454-83a6-b29c035d9816.png">
More variables

<img width="380" alt="image" src="https://user-images.githubusercontent.com/90798634/171289607-0d0dd27b-5c54-4244-b2f2-191ef9d642be.png">
Initiallizing the classes for the title screen. They will update as level changes.

<img width="665" alt="image" src="https://user-images.githubusercontent.com/90798634/171289764-b32eb3af-eb77-48b0-b370-12df751d8fd8.png">
Win screen and instructions screen (there are a lot of instructions, but you should be familiar with them in like 5-10 minutes of gameplay)

<img width="473" alt="image" src="https://user-images.githubusercontent.com/90798634/171292259-01f1fd4d-4243-4bbe-ae4e-54c3da2169a4.png">
Level Select Screen

<img width="444" alt="image" src="https://user-images.githubusercontent.com/90798634/171292430-1c7dd0d9-6c93-4afc-b06a-ae89bcd8e75d.png">
Painting the goal and doing the cube/button interaction. (We have an arraylist of cubes because we weren't sure if we should have more than 1 cube or not per level)

<img width="481" alt="image" src="https://user-images.githubusercontent.com/90798634/171292523-766c3792-0a33-41ef-aa2f-e95bf13b1ea3.png">
Title screen stuff

<img width="527" alt="image" src="https://user-images.githubusercontent.com/90798634/171292564-6670797a-45ae-4be6-a9cf-b90b229f3b97.png">
Checking if the level is beat, painting the walls, painting the lasers, checking the laser collisions

<img width="374" alt="image" src="https://user-images.githubusercontent.com/90798634/171292640-62283b5e-1444-450c-b91a-fd07ebb7a18f.png">
Pushing the cube(cube player collision)

<img width="320" alt="image" src="https://user-images.githubusercontent.com/90798634/171294856-cece3b57-caad-4434-bf35-69cd44c96aec.png">
Initializing portals, checking if the player moved off the screen, and enemy shooting/dying stuff

<img width="293" alt="image" src="https://user-images.githubusercontent.com/90798634/171294930-08fe75a4-d76a-431b-b816-fada6824e6c6.png">
Player movement, painting the player, and updating cube location if the player picked it up

<img width="470" alt="image" src="https://user-images.githubusercontent.com/90798634/171295059-889261fe-c135-43f6-b92b-06491f7c06d4.png">
Loops through the walls to check where the portal should be placed and does player wall collisions, also updates ground wall(the wall the player is standing on)

<img width="287" alt="image" src="https://user-images.githubusercontent.com/90798634/171295284-c96b9bee-6fa5-4a93-8459-515dc36c8895.png">
Teleportation stuff

<img width="773" alt="image" src="https://user-images.githubusercontent.com/90798634/171295736-a56b0e1f-f1dd-44fe-8bc7-d1ae36b4ec6a.png">
The methods to update a level. There are 2 ways shown here: The old way, which just puts everything in frame, or with the LevelManager Class, which uses classes for each level to make the code in frame shorter. 

<img width="539" alt="image" src="https://user-images.githubusercontent.com/90798634/171295943-393b29be-998a-4b3a-b048-3a2571d5153d.png">
The methods which updates the cube's location based on its collisions with walls. It's slightly bugged; sometimes the cube is able to hang upside down, and the collision with the top right corner is slightly inaccurate. But, these bugs are minor and hardly affect gameplay. 

<img width="431" alt="image" src="https://user-images.githubusercontent.com/90798634/171296110-39607f51-1aea-4059-a155-09afb68cb6f1.png">
The methods with deal with the teleportation of the cube and the player. These methods were annoying to code because of the amount of casework required. Teleportation was unexpectedly hard, and these methods were like 120 lines long each. 

<img width="551" alt="image" src="https://user-images.githubusercontent.com/90798634/171296245-37e79b9f-1702-4bd6-b2ab-938ce59a95c0.png">
The player and wall collision. 

<img width="435" alt="image" src="https://user-images.githubusercontent.com/90798634/171296297-f85f1567-9a53-4672-a9c5-77db70dadf39.png">
The method which checks if a rectangular object is hit by the laser. 

<img width="522" alt="image" src="https://user-images.githubusercontent.com/90798634/171296358-7b9cd459-8e09-4430-84e0-f19c8e404db2.png">
Methods computing the distance from the player to enemy(the enemy doesn't shoot if the player is too far), and the enemy shooting the laser

<img width="550" alt="image" src="https://user-images.githubusercontent.com/90798634/171296427-ec6da77a-2b86-401a-b05d-e960d7e8fa0e.png">
Method that checks if an object collided with a wall. 

<img width="440" alt="image" src="https://user-images.githubusercontent.com/90798634/171296472-2a525a98-12f0-4531-9374-e0e82a84d040.png">
Methods which check if the wall is on the left/right, or if the object is on the top/bottom

<img width="545" alt="image" src="https://user-images.githubusercontent.com/90798634/171296536-636a7e74-c5b4-4012-a0ff-fb98c54a5753.png">
Methods which do what the name says. 

<img width="587" alt="image" src="https://user-images.githubusercontent.com/90798634/171296598-39b0434e-6ec7-4c22-aa6f-54f67ac04a40.png">
(Part) The method which is in the enhanced wall for loop. The method places the portal and updates the player's location. If the player holds the cube, the hitbox is different. 

<img width="424" alt="image" src="https://user-images.githubusercontent.com/90798634/171296714-07a5d38a-cec7-4abf-bad4-dcc8b82291f7.png">
Empty methods which just exist because of ctrl C ctrl V

<img width="470" alt="image" src="https://user-images.githubusercontent.com/90798634/171296785-61312cf2-660b-4ea5-a979-da55f28cfaff.png">
Mouse click for portal shot location

<img width="593" alt="image" src="https://user-images.githubusercontent.com/90798634/171296818-afb8f599-2463-48fc-b714-e7b2e598908f.png">
Key Pressed method; WASD to move, space to pick up/release cube, r to restart level

<img width="313" alt="image" src="https://user-images.githubusercontent.com/90798634/171296898-fb2761b1-523d-4bd6-be1c-8aa5b8a96f30.png">
These methods check collision between the two objects listed in the parameters

<img width="532" alt="image" src="https://user-images.githubusercontent.com/90798634/171296964-6078ba36-fb8b-448f-a990-a206f0405c11.png">

Key released method
