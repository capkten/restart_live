package org.capten.live.service.bo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import org.capten.live.dao.UsersDao;
import org.capten.live.model.Users;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
public class UsersBo {

    public static final int LOGIN_SUCCESS = 0;

    public static final int LOGIN_USER_NOT_FOUND = 1;

    public static final String LOGIN_USER_NOT_FOUND_MSG = "user not found";

    public static final int LOGIN_FAIL = 2;

    public static final String LOGIN_FAIL_MSG = "password is wrong";

    public static final int REGISTER_USERNAME_IN = 10;

    public static final String REGISTER_USERNAME_IN_MSG = "username already exists";

    public static final int REGISTER_SUCCESS = 11;

    public static final String REGISTER_SUCCESS_MSG = "register success";

    public static final int REGISTER_FAIL = 12;

    public static final String REGISTER_FAIL_MSG = "register fail";

    private static final byte[] SECURITY_SALT = "capkin".getBytes();

    private UsersDao usersDao;

    /**
     * get token
     * @param username
     * @param password
     * @return
     */
    public String getToken(String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("username", username);
                put("password", password);
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };
        return JWTUtil.createToken(map, SECURITY_SALT);
    }

    /**
     * password encryption
     * @param password
     * @return
     */
    public String getEncryptedPassword(String password) {
        return SecureUtil.sha1(password);
    }

    public boolean checkUserExists(String username) {
        try {
            usersDao.getUserInfoByUserName(username);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
