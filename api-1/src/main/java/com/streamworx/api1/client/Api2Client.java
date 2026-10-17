package com.streamworx.api1.client;

import com.streamworx.api1.dto.PostList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api2", url = "${api2.url}")
public interface Api2Client {
    @GetMapping(value = "/api/posts", produces = MediaType.APPLICATION_XML_VALUE)
    PostList getPosts();
}