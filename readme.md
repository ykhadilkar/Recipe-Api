# Recipe API

## List all recipes 

`GET api/v1/recipes`

## Get a recipes

`GET api/v1/recipes/{id}`

## Build an image using buildpack
`./mvnw -Pnative spring-boot:build-image`

## Push image to registry
`docker tag recipe-api:0.0.1-SNAPSHOT harbor.lab.khadilkar.net:library/recipe-api:latest`
