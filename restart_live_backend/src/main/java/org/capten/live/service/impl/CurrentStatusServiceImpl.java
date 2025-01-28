package org.capten.live.service.impl;

import org.capten.live.dao.CurrentStatusDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.CurrentStatus;
import org.capten.live.service.CurrentStatusService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
