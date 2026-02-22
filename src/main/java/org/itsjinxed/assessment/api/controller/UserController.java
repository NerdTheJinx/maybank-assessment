package org.itsjinxed.assessment.api.controller;

import lombok.RequiredArgsConstructor;
import org.itsjinxed.assessment.api.model.User;
import org.itsjinxed.assessment.service.out.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> get(Pageable pageable) {
        var pagedUsers = userService.fetch(pageable);
        return ResponseEntity.ok(pagedUsers);
    }

}
