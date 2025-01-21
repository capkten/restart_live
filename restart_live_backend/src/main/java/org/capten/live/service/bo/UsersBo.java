package org.capten.live.service.bo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import org.capten.live.dao.UsersDao;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
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

    public static final String TOKEN_MSG = "Authorization";

    private static final byte[] SECURITY_SALT = "capkin".getBytes();

    private static final String USERNAME_STR = "username";

    private static final String PASSWORD_STR = "password";

    private static final String EXPIRE_TIME_STR = "expire_time";

    private static final Long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 15L;

    public static final int USER_NOT_FOUND = 1;

    public static final int USER_INFO_SUCCESS = 0;

    // update password code and msg
    public static final int USER_CHANGE_PASSWORD_SUCCESS = 100;

    public static final String USER_CHANGE_PASSWORD_SUCCESS_MSG = "change password success";

    public static final int USER_CHANGE_PASSWORD_USERNAME_ERR = 101;

    public static final String USER_CHANGE_PASSWORD_USERNAME_ERR_MSG = "username error";

    public static final int USER_CHANGE_PASSWORD_FAIL = 102;

    public static final String USER_CHANGE_PASSWORD_FAIL_MSG = "change password fail";

    @Autowired
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
                put(USERNAME_STR, username);
                put(PASSWORD_STR, password);
                put(EXPIRE_TIME_STR, System.currentTimeMillis() + EXPIRE_TIME);
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
        Users users = usersDao.getUserInfoByUserName(username);
        return users != null;
    }

    /**
     * check token whether is valid and check time is expired
     * @param token
     * @return
     */
    public boolean checkToken(String token) {
        try {
            JWTUtil.verify(token, SECURITY_SALT);
            final JWT jwt = JWTUtil.parseToken(token);
            jwt.getHeader(JWTHeader.TYPE);
            String payload = (String) jwt.getPayload(EXPIRE_TIME_STR);
            return Long.parseLong(payload) >= System.currentTimeMillis();
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserNameByToken(String token) {
        final JWT jwt = JWTUtil.parseToken(token);
        jwt.getHeader(JWTHeader.TYPE);
        return (String) jwt.getPayload(USERNAME_STR);
    }
}
