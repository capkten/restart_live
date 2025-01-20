package org.capten.live.controller;

import lombok.AllArgsConstructor;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.service.UsersService;
import org.capten.live.service.bo.UsersBo;
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
        ServiceResDto loginServiceDto = usersService.login(username, password);
        return switch (loginServiceDto.code()) {
            case UsersBo.LOGIN_SUCCESS -> ResponseVo.success(loginServiceDto.data());
            case UsersBo.LOGIN_FAIL -> ResponseVo.error(UsersBo.LOGIN_FAIL_MSG);
            case UsersBo.LOGIN_USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException(String.valueOf(loginServiceDto.code()));
        };
    }

    @PostMapping("/register")
    public ResponseVo register(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        ServiceResDto registerServiceDto = usersService.register(username, password);
        return switch (registerServiceDto.code()) {
            case UsersBo.REGISTER_SUCCESS -> ResponseVo.success(UsersBo.REGISTER_SUCCESS);
            case UsersBo.REGISTER_USERNAME_IN -> ResponseVo.error(UsersBo.REGISTER_USERNAME_IN_MSG);
            case UsersBo.REGISTER_FAIL -> ResponseVo.error(UsersBo.REGISTER_FAIL_MSG);
            default -> throw new IllegalStateException(String.valueOf(registerServiceDto.code()));
        };
    }
}
