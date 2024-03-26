package com.beomstagram.user.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.user.adapter.in.web.FindUserResultDto;
import com.beomstagram.user.adapter.out.persistence.UserEntity;
import com.beomstagram.user.adapter.out.persistence.UserMapper;
import com.beomstagram.user.application.port.in.FindUserUseCase;
import com.beomstagram.user.application.port.out.FindUserPort;
import com.beomstagram.user.application.port.out.FollowUserPort;
import com.beomstagram.user.domain.FollowingUser;
import com.beomstagram.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindUserService implements FindUserUseCase {

    private final FindUserPort findUserPort;
    private final FollowUserPort followUserPort;
    private final UserMapper userMapper;

    @Override
    public User findByUserId(Long userId) {
        UserEntity userEntity = findUserPort.findByUserId(userId);

        return userMapper.mapToDomain(userEntity);
    }

    @Override
    public Page<FindUserResultDto> findAllByNameOrNickName(Long userId, String keyword, Pageable pageable) {

        Page<UserEntity> userEntities = findUserPort.findAllByNameOrNickName(keyword, pageable);

        List<FindUserResultDto> findUserResults = userEntities.stream()
                .map(userEntity -> {
                    Long targetUserId = userEntity.getId();
                    int commonFollowersCount = followUserPort.findCommonFollowersCount(userId, targetUserId);
                    return FindUserResultDto.createFindUserResult(userEntity, commonFollowersCount);
                })
                .toList();

        return new PageImpl<>(findUserResults, pageable, userEntities.getTotalElements());

    }

    @Override
    public Boolean isExistsUser(Long userId) {
        return findUserPort.isExistsUser(userId);
    }

    @Override
    public List<FollowingUser> findFollowingById(Long userId) {
        List<UserEntity> followingUserEntityList = findUserPort.findFollowingById(userId);

        return followingUserEntityList.stream()
                .map(userMapper::mapToFollowingUser)
                .toList();
    }

    @Override
    public List<FollowingUser> findFollowerById(Long userId) {
        List<UserEntity> followerEntityList = findUserPort.findFollowersById(userId);

        return followerEntityList.stream()
                .map(userMapper::mapToFollowingUser)
                .toList();
    }
}
