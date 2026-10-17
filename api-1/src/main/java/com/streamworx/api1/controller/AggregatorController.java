package com.streamworx.api1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamworx.api1.client.Api2Client;
import com.streamworx.api1.client.ExternalClient;
import com.streamworx.api1.dto.PostList;
import com.streamworx.api1.dto.Todo;
import com.streamworx.api1.dto.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AggregatorController {

    private final Api2Client api2Client;
    private final ExternalClient externalClient;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public AggregatorController(Api2Client api2Client, ExternalClient externalClient, StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.api2Client = api2Client;
        this.externalClient = externalClient;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

        @GetMapping(value = "/users/{id}/detail", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getUserDetail(@PathVariable Long id) throws Exception {
        Map<String, Object> response = new HashMap<>();

        // 1. Ambil user dari Redis
        String userJson = redisTemplate.opsForValue().get("user:" + id);
        if (userJson != null) {
            User user = objectMapper.readValue(userJson, User.class);
            response.put("user", user);
        }

        // 2. Ambil todos dari API eksternal
        List<Todo> todos = externalClient.getTodos();
        response.put("todos", todos);

        // 3. Ambil posts dari API-2 (XML)
        PostList postList = api2Client.getPosts();
        response.put("posts", postList.getPosts());

        return response;
    }
}