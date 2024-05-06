package com.beomstagram.image.adapter.in.web;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteImageRequest {
    private List<String> imageNames;
}
