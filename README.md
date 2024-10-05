WoW Item Tracker Backend
This is a backend application built using Java Spring Boot for tracking wanted items in World of Warcraft (WoW). It allows players to manage a list of in-game items they are looking for and track their progress

Features
Item Management: Add, update, and delete items you are tracking.
Progress Tracking: Keep track of which items you have acquired and which are still needed(Not yet implemented).
RESTful API: Provides RESTful endpoints to interact with the system.


Technologies Used
Java: The backend is built using Java.
Spring Boot 3.x: For creating RESTful APIs and managing the backend logic..
MongoDB: Supported databases (configurable).
Maven: For project management and dependency management.
ngrok: to run both spring boot and front end project locally on the same machine
Docker: packaged as a Docker container for easy deployment and replaceing the need for ngrok




Running locally - Getting Started
Prerequisites
Ensure you have the following installed:

Java 
Maven
MongoDB atlas web and/or MongoDB Compass
Git
ngrok
Docker



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

To run the application, you can build the Docker image using the provided Dockerfile and start it using Docker Compose or docker run commands

To see the web application for API, please see my repo https://github.com/pross99/wowItemTracker




NOTE: 

API CAN BE PULLED FROM https://springtransmogapi5-714423430443.europe-west1.run.app 
FREE TRIAL with 90 DAYS 30/09/2024
