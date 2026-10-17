package com.streamworx.api1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamworx.api1.client.ExternalClient;
import com.streamworx.api1.dto.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RedisLoader implements CommandLineRunner {

    private final StringRedisTemplate redisTemplate;
    private final ExternalClient externalClient;
    private final ObjectMapper objectMapper;

    public RedisLoader(StringRedisTemplate redisTemplate, ExternalClient externalClient, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.externalClient = externalClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mengambil data user dari API eksternal...");
        List<User> users = externalClient.getUsers();
        
        for (User user : users) {
            String json = objectMapper.writeValueAsString(user);
            redisTemplate.opsForValue().set("user:" + user.getId(), json);
        }
        System.out.println("Berhasil menyimpan " + users.size() + " user ke Redis!");
    }
}