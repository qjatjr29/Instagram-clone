package com.beomstagram.comment.adapter.out.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feed-service")
public interface FeedFeignClient {

    @GetMapping("/feed-service/exists/{feedId}")
    boolean isExistsFeed(@PathVariable("feedId") Long feedId);

}
