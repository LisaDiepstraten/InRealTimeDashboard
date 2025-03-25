#!/bin/bash

# Ensure MySQL container exists, create it if not
if [ ! "$(docker ps -q -f name=idk_db_dev)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=idk_db_dev)" ]; then
        # Cleanup any stopped container
        echo "Removing stopped container: idk_db_dev"
        docker rm idk_db_dev
    fi
    # Run new MySQL container
    echo "Running new MySQL container: idk_db_dev"
    docker run --name idk_db_dev -e MYSQL_DATABASE=idk_db_dev -e MYSQL_ROOT_PASSWORD=idk_db_dev_pw123 -p 12345:3306 -d mysql
else
    echo "MySQL container idk_db_dev is already running."
fi