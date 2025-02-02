package org.capten.live.service.impl;

import org.capten.live.model.Tasks;
import org.capten.live.mapper.TasksMapper;
import org.capten.live.service.ITasksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@Service
public class TasksServiceImpl extends ServiceImpl<TasksMapper, Tasks> implements ITasksService {

}
