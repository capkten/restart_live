package org.capten.live.controller;

import lombok.AllArgsConstructor;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/users")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    @PostMapping("/login")
    public ResponseVo login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {

    }
}
