package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.model.FinalAim;
import org.capten.live.service.IFinalAimService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@RestController
@RequestMapping("/api/v1/finalAim")
public class FinalAimController {

    @Autowired
    private IFinalAimService finalAimService;

    @RequestMapping("/all")
    public ResponseVo getAll(HttpServletRequest request) {
        ServiceResDto res = finalAimService.getAll(request.getHeader(UsersBo.TOKEN_MSG));
        return switch (res.code()) {
            case UsersBo.USER_FINAL_AIM_GET_SUCCESS -> ResponseVo.success(res.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException("Unexpected value: " + res.code());
        };
    }

    @RequestMapping("/change")
    public ResponseVo change(@RequestBody List<FinalAim> finalAims, HttpServletRequest request) {
        ServiceResDto res = finalAimService.change(finalAims, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (res.code()) {
            case UsersBo.USER_FINAL_AIM_GET_SUCCESS -> ResponseVo.success(res.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException("Unexpected value: " + res.code());
        };
    }
}

