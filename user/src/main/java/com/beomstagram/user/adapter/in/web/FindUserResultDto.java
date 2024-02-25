package com.beomstagram.user.adapter.in.web;

import com.beomstagram.user.adapter.out.persistence.UserEntity;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FindUserResultDto {

    private final Long userId;
    private final String name;
    private final String profileImage;
    private final int commonFollowersCount;
    private final LocalDateTime createdAt;

    public static FindUserResultDto createFindUserResult(UserEntity user, int commonFollowersCount) {
        return new FindUserResultDto(user.getId(),
                user.getName(),
                user.getProfileImage(),
                commonFollowersCount,
                user.getCreatedAt());
    }

}
