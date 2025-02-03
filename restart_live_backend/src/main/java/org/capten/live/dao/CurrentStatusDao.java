package org.capten.live.dao;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.catalina.User;
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
                .leftJoin(CurrentStatus.class, CurrentStatus::getUserId, Users::getId)
                .eq(Users::getUsername, userNameByToken)
                .selectAll(CurrentStatus.class);
        List<CurrentStatus> currentStatuses = currentStatusMapper.selectJoinList(eqWrapper);
        return currentStatuses;
    }

    public int removeIds(List<String> removeIds) {
        if (removeIds.isEmpty()) {
            return 1;
        }
        return currentStatusMapper.deleteByIds(removeIds);
    }

    public int updateCurrentStatus(List<CurrentStatus> insertList, List<CurrentStatus> updateList) {
//        if (!insertList.isEmpty()) {
//            if (currentStatusMapper.insertSelectiveList(insertList) <= 0) {
//                return 0;
//            }
//            ;
//        }
//        if (!updateList.isEmpty()){
//            if (currentStatusMapper.updateCurrentStatusList(updateList) <= 0) {
//                return 0;
//            }
//        }
//        return 1;
        return 0;
    }
}
