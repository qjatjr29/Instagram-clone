package com.beomstagram.feed.application.service;

import com.beomstagram.common.annotation.UseCase;
import com.beomstagram.feed.adapter.out.persistence.FeedEntity;
import com.beomstagram.feed.adapter.out.persistence.FeedMapper;
import com.beomstagram.feed.application.port.in.FindFeedUseCase;
import com.beomstagram.feed.application.port.out.FindFeedPort;
import com.beomstagram.feed.domain.Feed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindFeedService implements FindFeedUseCase {

    private final FindFeedPort findFeedPort;
    private final FeedMapper feedMapper;

    @Override
    public Page<Feed> findAllByUserId(Long userId, Pageable pageable) {
        List<Feed> feedList = findFeedPort.findAllByUserId(userId, pageable)
                .getContent()
                .stream()
                .map(feedMapper::mapToDomain)
                .toList();

        return new PageImpl<>(feedList);
    }

    @Override
    public Feed findByFeedId(Long userId, Long feedId) {
        // todo: cache 처리
        // todo: userId와 feedId를 통해 Like service에 요청
        FeedEntity feedEntity = findFeedPort.findById(feedId);
        return feedMapper.mapToDomain(feedEntity);
    }

    @Override
    public Boolean isExistsFeed(Long feedId) {
        return findFeedPort.isExitsFeed(feedId);
    }
}
