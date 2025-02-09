package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.RuntimeTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
public interface IRuntimeTaskService extends IService<RuntimeTask> {


    ServiceResDto getAll(LocalDateTime start, LocalDateTime end, String token);

    ServiceResDto add(RuntimeTask runtimeTask, String token);

}
