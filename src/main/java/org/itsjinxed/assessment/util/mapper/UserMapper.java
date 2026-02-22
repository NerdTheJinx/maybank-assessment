package org.itsjinxed.assessment.util.mapper;

import org.itsjinxed.assessment.api.model.User;
import org.itsjinxed.assessment.service.out.model.UserFragment;

public class UserMapper {

    private UserMapper() {
    }

    public static User map(UserFragment userFragment) {
        return new User(
                userFragment.id(),
                userFragment.name(),
                userFragment.email(),
                userFragment.mobile()
        );
    }
}
