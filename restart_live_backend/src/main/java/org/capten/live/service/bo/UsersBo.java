package org.capten.live.service.bo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.capten.live.dao.UsersDao;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Date;

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

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

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

    public static final int USER_CHANGE_BG_FAIL_TYPE_ERR = 110;

    public static final String USER_CHANGE_BG_FAIL_TYPE_MSG = "change background image fail,because file is not img";

    public static final int USER_CHANGE_BG_FILE_SIZE_ERR = 111;

    public static final String USER_CHANGE_BG_FILE_SIZE_MSG = "change background image fail,because file size is too large, file should be lower 5MB";

    @Autowired
    private UsersDao usersDao;

    /**
     * get token
     * @param username
     * @param password
     * @return
     */
    public String getToken(String username, String password) {

        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + EXPIRE_TIME;
        Date now = new Date(nowMillis);
        Date exp = new Date(expMillis);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(exp)
                .claim(PASSWORD_STR, password)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
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
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserNameByToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
