package org.capten.live.service.impl;

import cn.hutool.crypto.SecureUtil;
import lombok.AllArgsConstructor;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.model.Users;
import org.capten.live.service.UsersService;
import org.capten.live.service.bo.UsersBo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {

    private UsersDao usersDao;

    private UsersBo usersBo;

    /**
     * login
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServiceResDto login(String username, String password) {
        try {
            Users user = usersDao.getUserInfoByUserName(username);
            // password encryption
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
}
