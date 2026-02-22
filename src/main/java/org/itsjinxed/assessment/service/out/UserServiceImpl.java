package org.itsjinxed.assessment.service.out;

import lombok.RequiredArgsConstructor;
import org.itsjinxed.assessment.api.model.User;
import org.itsjinxed.assessment.service.out.collection.UserDataCollector;
import org.itsjinxed.assessment.util.ValidationUtil;
import org.itsjinxed.assessment.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Value("${models.user.sortable}")
    private List<String> sortable;

    private final UserDataCollector userDataCollector;

    @Override
    public Page<User> fetch(Pageable pageable) {
        ValidationUtil.validateSort(pageable, sortable);

        var usersFlux = userDataCollector.collect();
        // use synchronous handling to reduce complexity at the moment
        var users = usersFlux.map(UserMapper::map).collectList().block();

        if (users == null) {
            throw new IllegalStateException("Empty user data from the external service!");
        }

        return getPagedContent(pageable, users);
    }

    private <T> PageImpl<T> getPagedContent(Pageable pageable, List<T> list) {
        var pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        var start = (int) pageRequest.getOffset();
        var end = Math.min((start + pageRequest.getPageSize()), list.size());
        List<T> pagedContent = (start >= end)
                ? List.of()
                : list.subList(start, end);
        return new PageImpl<>(pagedContent, pageRequest, list.size());
    }
}
