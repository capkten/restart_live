package org.capten.live.dao;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.capten.live.mapper.RuntimeTaskMapper;
import org.capten.live.model.RuntimeTask;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RuntimeTaskDao {

    @Autowired
    private RuntimeTaskMapper runtimeTaskMapper;

    public List<RuntimeTask> getAllByTimeAndUsername(LocalDateTime start, LocalDateTime end, String userNameByToken) {
        List<RuntimeTask> runtimeTasks = runtimeTaskMapper.selectJoinList(RuntimeTask.class, new MPJLambdaWrapper<RuntimeTask>()
                .selectAll(RuntimeTask.class)
                .leftJoin(Users.class, Users::getId, RuntimeTask::getUid)
                .eq(Users::getUsername, userNameByToken)
                .between(RuntimeTask::getBelongingTime, start, end));
        return runtimeTasks;
    }
}
