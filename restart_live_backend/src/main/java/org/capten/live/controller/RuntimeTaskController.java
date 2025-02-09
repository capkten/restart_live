package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.model.RuntimeTask;
import org.capten.live.service.IRuntimeTaskService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@RestController
@RequestMapping("/api/v1/runtimeTask")
public class RuntimeTaskController {

    @Autowired
    private IRuntimeTaskService runtimeTaskService;

    @Autowired
    private UsersBo usersBo;

    @GetMapping("/all")
    public ResponseVo getAll(@RequestParam("startDate") String startDateStr,@RequestParam("endDate") String endDateStr, HttpServletRequest request) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        ServiceResDto serviceResDto = runtimeTaskService.getAll(startDate.atStartOfDay(),endDate.atTime(23,59,59),request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_RUNTIME_TASK_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            default -> throw new IllegalStateException("Unexpected value: " + serviceResDto.code());
        };
    }

    @PostMapping("/add")
    public ResponseVo add(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        RuntimeTask runtimeTask = new RuntimeTask();
        runtimeTask.setIsFinish(Boolean.valueOf(map.get("isFinish").toString()));
        runtimeTask.setBelongingTime(LocalDateTime.parse(map.get("belongingTime").toString()));
        runtimeTask.setName(map.get("name").toString());
        runtimeTask.setRemark(map.get("remark").toString());
        runtimeTask.setStars(new BigDecimal(map.get("stars").toString()));
        runtimeTask.setExperience(Integer.parseInt(map.get("experience").toString()));
        ServiceResDto serviceResDto = runtimeTaskService.add(runtimeTask,request.getHeader(UsersBo.TOKEN_MSG));
        return switch (serviceResDto.code()) {
            case UsersBo.USER_RUNTIME_TASK_SUCCESS -> ResponseVo.success(serviceResDto.data());
            case UsersBo.USER_NOT_FOUND -> ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            case UsersBo.USER_RUNTIME_TASK_ERR -> ResponseVo.error(UsersBo.USER_RUNTIME_TASK_ERR_MSG);
            default -> throw new IllegalStateException("Unexpected value: " + serviceResDto.code());
        };

        
    }
}
