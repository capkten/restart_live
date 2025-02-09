package org.capten.live.service.impl;

import org.capten.live.dao.RuntimeTaskDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.model.RuntimeTask;
import org.capten.live.mapper.RuntimeTaskMapper;
import org.capten.live.model.Users;
import org.capten.live.service.IRuntimeTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@Service
public class RuntimeTaskServiceImpl extends ServiceImpl<RuntimeTaskMapper, RuntimeTask> implements IRuntimeTaskService {

    @Autowired
    private RuntimeTaskDao runtimeTaskDao;

    @Autowired
    private UsersBo usersBo;

    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RuntimeTaskMapper runtimeTaskMapper;

    @Override
    public ServiceResDto getAll(LocalDateTime start, LocalDateTime end, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (userNameByToken == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        List<RuntimeTask> runtimeTasks = runtimeTaskDao.getAllByTimeAndUsername(start,end,userNameByToken);
        return new ServiceResDto(UsersBo.USER_RUNTIME_TASK_SUCCESS, runtimeTasks);
    }

    @Override
    public ServiceResDto add(RuntimeTask runtimeTask, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (userNameByToken == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        Users user = usersDao.getUserInfoByUserName(userNameByToken);
        if (user == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        int insert = runtimeTaskMapper.insert(runtimeTask);
        if (!(insert > 0)) {
            return new ServiceResDto(UsersBo.USER_RUNTIME_TASK_ERR, null);
        }
        if (runtimeTask.getIsFinish()) {
            user.setExperience(user.getExperience() + runtimeTask.getExperience());
            int i = usersMapper.updateById(user);
            if (i > 0) {
                return new ServiceResDto(UsersBo.USER_RUNTIME_TASK_SUCCESS, null);
            }
        }
        return new ServiceResDto(UsersBo.USER_RUNTIME_TASK_ERR, null);
    }
}
