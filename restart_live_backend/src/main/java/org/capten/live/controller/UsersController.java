package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.capten.live.annotation.SkipTokenValidation;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.service.UsersService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    @SkipTokenValidation
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
    @SkipTokenValidation
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

    @GetMapping("/info")
    public ResponseVo info(HttpServletRequest request) {
        ServiceResDto userInfo = usersService.getUserInfo(request.getHeader(UsersBo.TOKEN_MSG));
        return switch (userInfo.code()) {
            case UsersBo.USER_INFO_SUCCESS -> ResponseVo.success(userInfo.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException(String.valueOf(userInfo.code()));
        };
    }

    @PostMapping("/updatePassword")
    public ResponseVo updatePassword(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     HttpServletRequest request) {
        ServiceResDto updatePass = usersService.updatePassword(username, password, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (updatePass.code()) {
            case UsersBo.USER_CHANGE_PASSWORD_SUCCESS -> ResponseVo.success(UsersBo.USER_CHANGE_PASSWORD_SUCCESS_MSG);
            case UsersBo.USER_CHANGE_PASSWORD_USERNAME_ERR ->
                    ResponseVo.error(UsersBo.USER_CHANGE_PASSWORD_USERNAME_ERR_MSG);
            case UsersBo.USER_CHANGE_PASSWORD_FAIL -> ResponseVo.error(UsersBo.USER_CHANGE_PASSWORD_FAIL_MSG);
            default -> throw new IllegalStateException(String.valueOf(updatePass.code()));
        };
    }

    @GetMapping("/config")
    public ResponseVo getConfig(HttpServletRequest request) {
        ServiceResDto configDto = usersService.getUserConfig(request.getHeader(UsersBo.TOKEN_MSG));
        return switch (configDto.code()) {
            case UsersBo.USER_INFO_SUCCESS -> ResponseVo.success(configDto.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException(String.valueOf(configDto.code()));
        };
    }

    @PostMapping("/config/icon")
    public ResponseVo updateIcon(@RequestParam("icon") String icon,
                                 HttpServletRequest request) {
        ServiceResDto updateIcon = usersService.updateIcon(icon, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (updateIcon.code()) {
            case UsersBo.USER_INFO_SUCCESS -> ResponseVo.success(updateIcon.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException(String.valueOf(updateIcon.code()));
        };
    }

    @PostMapping("/config/bg_img")
    public ResponseVo updateBackgroundImage(@RequestPart("file") MultipartFile file,
                                           HttpServletRequest request) {
        String token = request.getHeader(UsersBo.TOKEN_MSG);
        ServiceResDto updateBgImg = usersService.updateBackgroundImage(file, token);
        return switch (updateBgImg.code()) {
            case UsersBo.USER_INFO_SUCCESS -> ResponseVo.success(updateBgImg.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_CHANGE_BG_FAIL_TYPE_ERR -> ResponseVo.error(UsersBo.USER_CHANGE_BG_FAIL_TYPE_MSG);
            case UsersBo.USER_CHANGE_BG_FILE_SIZE_ERR -> ResponseVo.error(UsersBo.USER_CHANGE_BG_FILE_SIZE_MSG);
            default -> throw new IllegalStateException(String.valueOf(updateBgImg.code()));
        };
    }
}