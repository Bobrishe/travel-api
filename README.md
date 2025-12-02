Mini project: Two Spring Boot Microservices with Postgres & Docker

This repository contains two small applications (auth-api and data-api) and a PostgreSQL database running through docker-compose.

To run the app, use commands below from the parent folder:

docker compose up -d --build

!Important! Docker is using .env file, also default ports are 8080, 8081, 5432 (Main app on port 8080)

Available requests

curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"email":"login@main.com","password":"somepassword"}'
response: 201
* You can create users just with unique emails

curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"email":"login@mail.com","password":"somepassword"}'
response: 200 <token>
* If is all correct, you get JWT token for next step. IMPORTANT! JWH has a lifetime, 20 minutes by default, then you should create another.
** If you get an error, check credentials

curl -X POST http://localhost:8080/api/process -H "Authorization: Bearer <JWT_TOKEN>" -H "Content-Type: application/json" -d '{"text":"transform_me"}'
response: 200 { "result": "EM_MROFSNART" }

! If you have a failed response, try to turn to the previous step. Check the login/password or JWT.

Requests to data-api main app controls himself. 
