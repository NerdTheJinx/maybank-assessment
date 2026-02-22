package org.itsjinxed.assessment.service.out.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record UserFragment(
        Long id,
        String name,
        String email,
        @JsonAlias("phone") String mobile) implements DataFragment {
}
