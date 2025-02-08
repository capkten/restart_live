package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.service.IReviewTypeService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@RestController
@RequestMapping("/api/v1/reviewType")
public class ReviewTypeController {

    @Autowired
    private IReviewTypeService reviewTypeService;


    @GetMapping("/all")
    public ResponseVo all(HttpServletRequest request) {
        ServiceResDto serviceResDto = reviewTypeService.getAll(request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_REVIEW_TYPE_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_REVIEW_TYPE_ERR -> ResponseVo.error(UsersBo.USER_REVIEW_TYPE_ERR_MSG);
            default -> throw new RuntimeException("unknown error");
        };
    }

    @PostMapping("/add")
    public ResponseVo add(@RequestParam("name") String name, @RequestParam("sort") Integer sort, HttpServletRequest request) {
        ServiceResDto serviceResDto = reviewTypeService.add(name, sort, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_REVIEW_TYPE_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_REVIEW_TYPE_ERR -> ResponseVo.error(UsersBo.USER_REVIEW_TYPE_ERR_MSG);
            default -> throw new RuntimeException("unknown error");
        };
    }

    @GetMapping("/delete")
    public ResponseVo delete(@RequestParam("id") Integer id, HttpServletRequest request) {
        ServiceResDto serviceResDto = reviewTypeService.delete(id, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_REVIEW_TYPE_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_REVIEW_TYPE_ERR -> ResponseVo.error(UsersBo.USER_REVIEW_TYPE_ERR_MSG);
            default -> throw new RuntimeException("unknown error");
        };
    }

    @PostMapping("/change")
    public ResponseVo change(@RequestParam("id") Integer id, @RequestParam("name") String name, HttpServletRequest request) {
        ServiceResDto serviceResDto = reviewTypeService.changeName(id, name, request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_REVIEW_TYPE_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_REVIEW_TYPE_ERR -> ResponseVo.error(UsersBo.USER_REVIEW_TYPE_ERR_MSG);
            default -> throw new RuntimeException("unknown error");
        };
    }
}
