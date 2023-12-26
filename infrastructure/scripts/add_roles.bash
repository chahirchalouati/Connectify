#!/bin/bash

# Define the URL
url="http://localhost:9999/iam/roles"

# Define the role data
role_data='{
  "name": "exampleRole",
  "enabled": true
}'

# Make the POST request
curl -X POST -H "Content-Type: application/json" -H "DEV_USER: value" -d "$role_data" $url
