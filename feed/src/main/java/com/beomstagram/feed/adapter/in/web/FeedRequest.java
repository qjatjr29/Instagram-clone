package com.beomstagram.feed.adapter.in.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FeedRequest {
    private String caption;
    private List<List<String>> hashtags;
}
