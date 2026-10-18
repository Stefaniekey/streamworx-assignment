# Streamworx Assignment - Java Spring Boot

## How to Run
1. Clone this repository.
2. Make sure Docker Desktop is running.
3. Run `docker compose up --build` in the root directory.
4. Wait until all 5 services are up and running.

## API Testing Endpoints
Once the containers are running, you can test the APIs here:

- **API Gateway -> API 1 (JSON):** `http://localhost:8080/api1/users/1/detail`
- **API Gateway -> API 2 (XML):** `http://localhost:8080/api2/posts`
- **API 1 Direct (JSON):** `http://localhost:8081/api/users/1/detail`
- **API 2 Direct (XML):** `http://localhost:8082/api/posts`
