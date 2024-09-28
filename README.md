WoW Item Tracker Backend
This is a backend application built using Java Spring Boot for tracking wanted items in World of Warcraft (WoW). It allows players to manage a list of in-game items they are looking for, track their progress, and stay updated on their collection goals.

Features
Item Management: Add, update, and delete items you are tracking.
Progress Tracking: Keep track of which items you have acquired and which are still needed.
RESTful API: Provides RESTful endpoints to interact with the system.


Technologies Used
Java: The backend is built using Java.
Spring Boot 3.x: For creating RESTful APIs and managing the backend logic..
MongoDB: Supported databases (configurable).
Maven: For project management and dependency management.




Running locally - Getting Started
Prerequisites
Ensure you have the following installed:

Java 
Maven
MongoDB atlas web and/or MongoDB Compass
Git



Installation
Clone the repository:
git clone https://github.com/pross99/Transmogapi.git

cd Transmogapi.git

Configure the database:
set up the .env file using the .env.example as a template


Environment Variables
MONGO_DATABASE: name of the database
MONGO_USER: name of the user created under "Database Access"
MONGO_PASSWORD: password for the user
MONGO_CLUSTER: name of the cluster
