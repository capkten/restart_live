package org.capten.live.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.capten.live.dao.CurrentStatusDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.CurrentStatus;
import org.capten.live.model.Users;
import org.capten.live.service.CurrentStatusService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    @Autowired
    private UsersDao usersDao;

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
            log.info("Failed to get current status" + e);
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
        return executeUpdateCurrentStatus(currentStatusList, userNameByToken);
    }

    protected ServiceResDto executeUpdateCurrentStatus(List<CurrentStatus> currentStatusList, String username) {
        Users userInfoByUserName = usersDao.getUserInfoByUserName(username);
        Integer userId = userInfoByUserName.getId();
        List<Integer> removeIds = new ArrayList<>();
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
            if (currentStatus.getId() == null || currentStatus.getId() == 0){
                currentStatus.setUserId(userId);
                currentStatus.setId(null);
                currentStatus.setCreateTime(LocalDateTime.now());
                currentStatus.setUpdateTime(LocalDateTime.now());
                insertList.add(currentStatus);
            }else {
                currentStatus.setUpdateTime(LocalDateTime.now());
                currentStatus.setCreateTime(null);
                currentStatus.setVersion(null);
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
