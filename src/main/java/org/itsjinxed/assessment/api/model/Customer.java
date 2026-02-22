package org.itsjinxed.assessment.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

import static org.itsjinxed.assessment.constant.ServiceConstant.DEFAULT_MAX_CHARACTERS;
import static org.itsjinxed.assessment.constant.ServiceConstant.MOBILE_MAX_CHARACTERS;

public record Customer(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        UUID id,

        @NotBlank(message = "{customer.name.not-blank}")
        @Size(max = DEFAULT_MAX_CHARACTERS, message = "{customer.name.size}")
        String name,

        @NotBlank(message = "{customer.email.not-blank}")
        @Size(max = DEFAULT_MAX_CHARACTERS, message = "{customer.email.size}")
        String email,

        @NotBlank(message = "{customer.mobile.not-blank}")
        @Size(max = MOBILE_MAX_CHARACTERS, message = "{customer.mobile.size}")
        String mobile
) implements Serializable {
}
