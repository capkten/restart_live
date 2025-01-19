package org.capten.live.service.bo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWTUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UsersBo {

    public static final int LOGIN_SUCCESS = 0;

    public static final int LOGIN_USER_NOT_FOUND = 1;

    public static final int LOGIN_FAIL = 2;

    private static final byte[] SECURITY_SALT = "capkin".getBytes();

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
}
