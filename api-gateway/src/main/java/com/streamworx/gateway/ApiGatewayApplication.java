package com.streamworx.gateway;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
@RestController
public class ApiGatewayApplication {

    private final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @GetMapping("/api1/**")
    public ResponseEntity<String> routeToApi1(HttpServletRequest request) throws Exception {
        String path = request.getRequestURI().replace("/api1", "");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://api-1:8081/api" + path))
                .GET()
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.body());
    }

    @GetMapping("/api2/**")
    public ResponseEntity<String> routeToApi2(HttpServletRequest request) throws Exception {
        String path = request.getRequestURI().replace("/api2", "");
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://api-2:8082/api" + path))
                .GET()
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(response.statusCode())
                .contentType(MediaType.APPLICATION_XML) // INI DIA YANG MEMBUAT JADI XML
                .body(response.body());
    }
}