package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.model.CurrentStatus;
import org.capten.live.service.CurrentStatusService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class CurrentStatusController {

    @Autowired
    private CurrentStatusService currentStatusService;

    @GetMapping("/all")
    public ResponseVo current(HttpServletRequest request) {
        ServiceResDto currentStatusDto = currentStatusService.getCurrentStatus(request.getHeader(UsersBo.TOKEN_MSG));
        return switch (currentStatusDto.code()) {
            case UsersBo.USER_CURRENT_STATUS ->  ResponseVo.success(currentStatusDto.data());
            case UsersBo.USER_CURRENT_STATUS_ERR -> ResponseVo.error(UsersBo.USER_CURRENT_STATUS_ERR_MSG);
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default ->  ResponseVo.error("unknown error");
        };
    }

    @PostMapping("/change")
    public ResponseVo change(@RequestBody List<CurrentStatus> currentStatusList,
                             HttpServletRequest request) {
        ServiceResDto currentStatusDto = currentStatusService.updateCurrentStatus(request.getHeader(UsersBo.TOKEN_MSG), currentStatusList);
        return switch (currentStatusDto.code()) {
            case UsersBo.USER_CURRENT_STATUS ->  ResponseVo.success(currentStatusDto.data());
            case UsersBo.USER_CURRENT_STATUS_ERR -> ResponseVo.error(UsersBo.USER_CURRENT_STATUS_ERR_MSG);
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default ->  ResponseVo.error("unknown error");
        };
    }
}
