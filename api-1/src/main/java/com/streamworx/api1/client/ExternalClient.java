package com.streamworx.api1.client;

import com.streamworx.api1.dto.Todo;
import com.streamworx.api1.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "external", url = "${external.url}")
public interface ExternalClient {
    @GetMapping("/todos")
    List<Todo> getTodos();

    @GetMapping("/users")
    List<User> getUsers();
}