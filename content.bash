#!/bin/bash

# Find all bootstrap.yaml files and write the YAML content to them
find ./ -type f -name 'bootstrap.yaml' -exec bash -c '
  for file do
    dirname=$(dirname "$1")
    parentdir=$(basename "$dirname")
    yaml_content=$(cat <<EOF
spring:
  application:
    name: $parentdir-service
  cloud:
    config:
      uri: http://localhost:8888
    openfeign:
      circuitbreaker:
        alphanumeric-ids:
          enabled: true
EOF
)

    # Write the YAML content to the current bootstrap.yaml file
    echo "$yaml_content" > "$file"
  done
' bash {} +
