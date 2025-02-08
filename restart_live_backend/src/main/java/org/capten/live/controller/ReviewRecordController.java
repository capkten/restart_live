package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.model.ReviewRecord;
import org.capten.live.service.IReviewRecordService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 复盘记录 前端控制器
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@RestController
@RequestMapping("/api/v1/reviewRecord")
public class ReviewRecordController {

    @Autowired
    private IReviewRecordService reviewRecordService;

    @GetMapping("/all")
    public ResponseVo getAll(HttpServletRequest request) {
        ServiceResDto serviceResDto = reviewRecordService.getAllWithRecordType(request.getHeader(UsersBo.TOKEN_MSG));
        switch (serviceResDto.code()) {
            case UsersBo.USER_REVIEW_GET_SUCCESS -> {
                return ResponseVo.success(serviceResDto.data());
            }
            case UsersBo.USER_NOT_FOUND -> {
                return ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + serviceResDto.code());
            }
        }
    }

    @PostMapping("/add")
    public ResponseVo add(@RequestBody Map<String, Object> reviewRecord, HttpServletRequest request) {
        ReviewRecord reviewRecord1 = new ReviewRecord();
        LocalDate localDate = LocalDate.parse(reviewRecord.get("date").toString());
        reviewRecord1.setDate(localDate.atStartOfDay());
        reviewRecord1.setRtId(Integer.parseInt(reviewRecord.get("rtId").toString()));
        reviewRecord1.setContent(reviewRecord.get("content").toString());
        ServiceResDto serviceResDto = reviewRecordService.add(reviewRecord1, request.getHeader(UsersBo.TOKEN_MSG));
        switch (serviceResDto.code()) {
            case UsersBo.USER_REVIEW_ADD_SUCCESS -> {
                return ResponseVo.success(serviceResDto.data());
            }
            case UsersBo.USER_NOT_FOUND -> {
                return ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            }
            case UsersBo.USER_REVIEW_ADD_ERR -> {
                return ResponseVo.error(UsersBo.USER_REVIEW_ADD_ERR_MSG);
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + serviceResDto.code());
            }
        }
    }

    @GetMapping("/delete")
    public ResponseVo delete(@RequestParam("id") Integer id, HttpServletRequest request) {
        boolean b = reviewRecordService.deleteByIdWithUser(id, request.getHeader(UsersBo.TOKEN_MSG));
        if (b) {
            return ResponseVo.success();
        }
        return ResponseVo.error("删除失败");
    }

}
