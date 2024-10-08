#!/bin/bash

docker build -t poke_api-backend:latest ./backend
docker build -t poke_api-frontend:latest ./frontend

docker run -d -p 8080:8080 --name backend-container poke_api-backend:latest

docker run -d -p 3000:3000 --name frontend-container poke_api-frontend:latest

docker ps
