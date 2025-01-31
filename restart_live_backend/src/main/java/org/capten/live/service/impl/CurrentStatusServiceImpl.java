package org.capten.live.service.impl;

import org.capten.live.dao.CurrentStatusDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.CurrentStatus;
import org.capten.live.service.CurrentStatusService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CurrentStatusServiceImpl implements CurrentStatusService {

    @Autowired
    private CurrentStatusDao currentStatusDao;

    @Autowired
    private UsersBo usersBo;

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
        List<CurrentStatus> currentInStatus = (List<CurrentStatus>) currentStatus.data();
        List<Object> removeIds = new ArrayList<>();
        // 将currentStatus.data中的，没有在currentStatusList中的id，添加到removeIds中
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
        currentStatusList.forEach(c -> {
            if (c.getId() == null) {
                c.setId(UUID.randomUUID());
            }
        });
        int delete = currentStatusDao.removeIds(removeIds);
        if (delete > 0) {
            int update = currentStatusDao.updateCurrentStatus(currentStatusList);
        }
        return null;
    }
}
