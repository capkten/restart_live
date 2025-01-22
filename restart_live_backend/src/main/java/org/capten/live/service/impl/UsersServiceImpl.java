package org.capten.live.service.impl;

import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;
import org.capten.live.dao.ConfigDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.Config;
import org.capten.live.model.Users;
import org.capten.live.service.UsersService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UsersBo usersBo;

    @Autowired
    private ConfigDao configDao;

    private Logger log = Logger.getLogger(UsersServiceImpl.class.getName());

    @Override
    public ServiceResDto login(String username, String password) {
        try {
            Users user = usersDao.getUserInfoByUserName(username);
            password = usersBo.getEncryptedPassword(password);
            if (user.getPassword().equals(password)) {
                String token = usersBo.getToken(username, password);
                return new ServiceResDto(UsersBo.LOGIN_SUCCESS, token);
            } else {
                return new ServiceResDto(UsersBo.LOGIN_FAIL, null);
            }
        } catch (NoSuchElementException e) {
            return new ServiceResDto(UsersBo.LOGIN_USER_NOT_FOUND, null);
        }
    }

    @Override
    public ServiceResDto register(String username, String password) {
        if (usersBo.checkUserExists(username)) {
            return new ServiceResDto(UsersBo.REGISTER_USERNAME_IN, null);
        }
        password = usersBo.getEncryptedPassword(password);
        Users user = new Users();
        user.setId(UUID.randomUUID());
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(DateTime.now());
        user.setUpdateTime(DateTime.now());
        if (usersDao.registerUser(user)) {
            // create user config
            if (configDao.createBaseConfig(user.getId())) {
                return new ServiceResDto(UsersBo.REGISTER_SUCCESS, null);
            }
            return new ServiceResDto(UsersBo.REGISTER_FAIL, null);
        } else {
            return new ServiceResDto(UsersBo.REGISTER_FAIL, null);
        }
    }

    @Override
    public ServiceResDto getUserInfo(String token) {
        String username = usersBo.getUserNameByToken(token);
        Users users = usersDao.getUserInfoByUserName(username);
        if (users == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        } else {
            return new ServiceResDto(UsersBo.USER_INFO_SUCCESS, users);
        }
    }

    @Override
    public ServiceResDto updatePassword(String username, String password, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (!userNameByToken.equals(username)) {
            return new ServiceResDto(UsersBo.USER_CHANGE_PASSWORD_USERNAME_ERR, null);
        } else {
            password = usersBo.getEncryptedPassword(password);
            Users users = usersDao.getUserInfoByUserName(username);
            users.setPassword(password);
            users.setUpdateTime(DateTime.now());
            if (usersDao.updateUser(users)) {
                return new ServiceResDto(UsersBo.USER_CHANGE_PASSWORD_SUCCESS, null);
            } else {
                return new ServiceResDto(UsersBo.USER_CHANGE_PASSWORD_FAIL, null);
            }
        }
    }

    @Override
    public ServiceResDto getUserConfig(String token) {
        String username = usersBo.getUserNameByToken(token);
        Config config = configDao.getConfigByUserName(username);
        log.info("getUserConfig: " + config.toString());
        if (config == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        return new ServiceResDto(UsersBo.USER_INFO_SUCCESS, config);
    }
}