package org.capten.live.dao;

import org.capten.live.mapper.CurrentStatusMapper;
import org.capten.live.model.CurrentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrentStatusDao {

    @Autowired
    private CurrentStatusMapper currentStatusMapper;

    public List<CurrentStatus> getCurrentStatus(String userNameByToken) {
//        return currentStatusMapper.getCurrentStatusByUsername(userNameByToken);
        return new ArrayList<>();
    }

    public int removeIds(List<Object> removeIds) {
//        if (removeIds.isEmpty()) {
//            return 1;
//        }
//        return currentStatusMapper.updateIsDeleteByIds(removeIds);
        return 0;
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
