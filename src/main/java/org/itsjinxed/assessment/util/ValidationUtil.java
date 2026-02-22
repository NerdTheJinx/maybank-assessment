package org.itsjinxed.assessment.util;

import jakarta.validation.ConstraintViolation;
import org.itsjinxed.assessment.exception.DataValidationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static void validateSort(Pageable pageable, List<String> sortable) {
        if (sortable.isEmpty()){
            return; // assume all fields are allowed
        }
        var invalidSorts = pageable.getSort().get()
                .map(Sort.Order::getProperty)
                .filter(Predicate.not(sortable::contains))
                .toList();
        if (!invalidSorts.isEmpty()) {
            throw new IllegalArgumentException("The sort contains illegal elements!");
        }
    }

    public static <T> void validateConstraint(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            var messages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            throw new DataValidationException(messages);
        }
    }
}
