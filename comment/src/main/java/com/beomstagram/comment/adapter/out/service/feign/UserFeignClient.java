package com.beomstagram.comment.adapter.out.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @GetMapping("/user-service/{userId}")
    User findByUserId(@PathVariable("userId") Long userId);

}
