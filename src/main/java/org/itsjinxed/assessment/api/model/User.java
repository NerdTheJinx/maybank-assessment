package org.itsjinxed.assessment.api.model;

import java.io.Serializable;

public record User(
        Long id,
        String name,
        String email,
        String mobile
) implements Serializable {
}
