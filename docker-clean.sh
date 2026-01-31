#!/bin/bash

echo "Starting Docker cleanup..."

echo "1. Stopping containers..."
docker stop $(docker ps -aq)

echo "2. Removing containers..."
docker rm $(docker ps -aq)

echo "3. Removing volumes..."
docker volume rm $(docker volume ls -q)

echo "4. Removing images..."
docker rmi $(docker images -q)

echo "5. Pruning system..."
docker system prune -a -f

echo "Done."
echo "> Containers:"
docker ps -a
echo "> Volumes:"
docker volume ls
echo "> Images:"
docker images

