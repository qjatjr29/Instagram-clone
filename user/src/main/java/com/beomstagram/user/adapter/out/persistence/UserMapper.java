package com.beomstagram.user.adapter.out.persistence;

import com.beomstagram.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToDomain(UserEntity userEntity) {
        return User.generateUser(userEntity.getEmail(),
                userEntity.getName(),
                userEntity.getNickname(),
                userEntity.getPhoneNumber(),
                userEntity.getPassword(), "");
    }

}
