package org.itsjinxed.assessment.service.out;

import org.itsjinxed.assessment.api.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> fetch(Pageable pageable);
}
