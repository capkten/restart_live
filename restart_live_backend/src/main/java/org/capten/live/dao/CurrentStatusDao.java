package org.capten.live.dao;

import org.capten.live.mapper.CurrentStatusMapper;
import org.capten.live.model.CurrentStatus;
import org.capten.live.model.CurrentStatusExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrentStatusDao {

    @Autowired
    private CurrentStatusMapper currentStatusMapper;

    public List<CurrentStatus> getCurrentStatus(String userNameByToken) {
        return currentStatusMapper.getCurrentStatusByUsername(userNameByToken);
    }
}
