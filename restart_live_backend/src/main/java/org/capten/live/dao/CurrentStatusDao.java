package org.capten.live.dao;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.catalina.User;
import org.apache.ibatis.executor.BatchResult;
import org.capten.live.mapper.CurrentStatusMapper;
import org.capten.live.model.CurrentStatus;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrentStatusDao {

    @Autowired
    private CurrentStatusMapper currentStatusMapper;

    public List<CurrentStatus> getCurrentStatus(String userNameByToken) {
        MPJLambdaWrapper<CurrentStatus> eqWrapper = new MPJLambdaWrapper<CurrentStatus>()
                .leftJoin(Users.class, Users::getId, CurrentStatus::getUserId)
                .eq(Users::getUsername, userNameByToken)
                .selectAll(CurrentStatus.class);
        List<CurrentStatus> currentStatuses = currentStatusMapper.selectJoinList(eqWrapper);
        return currentStatuses;
    }

    public int removeIds(List<Integer> removeIds) {
        if (removeIds.isEmpty()) {
            return 1;
        }
        return currentStatusMapper.deleteByIds(removeIds);
    }

    public int updateCurrentStatus(List<CurrentStatus> insertList, List<CurrentStatus> updateList) {
        List<BatchResult> insertResults = currentStatusMapper.insert(insertList);
        List<BatchResult> updateResults = currentStatusMapper.updateById(updateList);

        int affectedRows = 0;
        boolean allOperationsSuccessful = true;

        for (BatchResult result : insertResults) {
            if (result.getUpdateCounts() == null || result.getUpdateCounts().length == 0) {
                allOperationsSuccessful = false;
            } else {
                for (int count : result.getUpdateCounts()) {
                    if (count <= 0) {
                        allOperationsSuccessful = false;
                    }
                    affectedRows += count;
                }
            }
        }

        for (BatchResult result : updateResults) {
            if (result.getUpdateCounts() == null || result.getUpdateCounts().length == 0) {
                allOperationsSuccessful = false;
            } else {
                for (int count : result.getUpdateCounts()) {
                    if (count <= 0) {
                        allOperationsSuccessful = false;
                    }
                    affectedRows += count;
                }
            }
        }

        // 如果所有操作都成功，返回受影响的行数；否则，可以返回一个特殊值或抛出异常
        return allOperationsSuccessful ? affectedRows : 0;
    }

}
