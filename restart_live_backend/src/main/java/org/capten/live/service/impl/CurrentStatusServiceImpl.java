package org.capten.live.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.capten.live.dao.CurrentStatusDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.CurrentStatus;
import org.capten.live.service.CurrentStatusService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CurrentStatusServiceImpl implements CurrentStatusService {

    @Autowired
    private CurrentStatusDao currentStatusDao;

    @Autowired
    private UsersBo usersBo;

    private Logger log = Logger.getLogger(CurrentStatusServiceImpl.class.getName());

    @Override
    public ServiceResDto getCurrentStatus(String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (userNameByToken == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        try {
            List<CurrentStatus> currentStatusList = currentStatusDao.getCurrentStatus(userNameByToken);
            return new ServiceResDto(UsersBo.USER_CURRENT_STATUS, currentStatusList);
        }catch (Exception e) {
            return new ServiceResDto(UsersBo.USER_CURRENT_STATUS_ERR, null);
        }
    }

    @Override
    public ServiceResDto updateCurrentStatus(String token, List<CurrentStatus> currentStatusList) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        ServiceResDto currentStatus = getCurrentStatus(token);
        switch (currentStatus.code()) {
            case UsersBo.USER_NOT_FOUND, UsersBo.USER_CURRENT_STATUS_ERR: return currentStatus;
            default: break;
        }
        try {
            return executeUpdateCurrentStatus(currentStatusList, userNameByToken);
        } catch (Exception e) {
            return new ServiceResDto(UsersBo.USER_CURRENT_STATUS_ERR, null);
        }
    }

    @Transactional
    protected ServiceResDto executeUpdateCurrentStatus(List<CurrentStatus> currentStatusList, String username) {
        List<String> removeIds = new ArrayList<>();
        // 将currentStatus.data中的，没有在currentStatusList中的id，添加到removeIds中
        List<CurrentStatus> currentInStatus = currentStatusDao.getCurrentStatus(username);
        currentInStatus.forEach(c -> {
            boolean flag = true;
            for (CurrentStatus currentStatus1 : currentStatusList) {
                if (c.getId().equals(currentStatus1.getId())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                removeIds.add(c.getId());
            }
        });
        List<CurrentStatus> insertList = new ArrayList<>();
        List<CurrentStatus> updateList = new ArrayList<>();
        for (CurrentStatus currentStatus : currentStatusList) {
            if (currentStatus.getId() == null || currentStatus.getId() != null){
                insertList.add(currentStatus);
            }else {
                updateList.add(currentStatus);
            }
        }
        int delete = currentStatusDao.removeIds(removeIds);
        if (delete > 0) {
            int update = currentStatusDao.updateCurrentStatus(insertList, updateList);
            if(update > 0) {
                return new ServiceResDto(UsersBo.USER_CURRENT_STATUS, currentStatusList);
            }
        }
        throw new RuntimeException("Failed to update current status");
    }
}
