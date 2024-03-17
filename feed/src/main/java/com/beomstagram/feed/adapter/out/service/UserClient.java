package com.beomstagram.feed.adapter.out.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping(value = "/user-service/{userId}")
    User findUser(@PathVariable("userId") Long userId);

    @GetMapping(value = "/user-service/exists/{userId}")
    Boolean isExistsUser(@PathVariable("userId") Long userId);
}
