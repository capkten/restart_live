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

    public static final int USER_CURRENT_STATUS = 120;

    public static final String USER_CURRENT_STATUS_MSG = "get current status success";

    public static final int USER_CURRENT_STATUS_ERR = 121;

    public static final String USER_CURRENT_STATUS_ERR_MSG = "get current status fail";

    public static final int USER_FINAL_AIM_GET_SUCCESS = 131;

    public static final String USER_FINAL_AIM_GET_SUCCESS_MSG = "get final aim success";

    public static final int USER_REVIEW_TYPE_SUCCESS = 141;

    public static final String USER_REVIEW_TYPE_SUCCESS_MSG = "get review type success";

    public static final int USER_REVIEW_TYPE_ERR = 142;

    public static final String USER_REVIEW_TYPE_ERR_MSG = "get review type fail";

    public static final int USER_REVIEW_GET_SUCCESS = 151;

    public static final String USER_REVIEW_GET_SUCCESS_MSG = "get review success";

    public static final int USER_REVIEW_GET_ERR = 152;

    public static final String USER_REVIEW_GET_ERR_MSG = "get review fail";

    public static final int USER_REVIEW_ADD_SUCCESS = 161;

    public static final String USER_REVIEW_ADD_SUCCESS_MSG = "add review success";

    public static final int USER_REVIEW_ADD_ERR = 162;

    public static final String USER_REVIEW_ADD_ERR_MSG = "add review fail";

    public static final int USER_REVIEW_CHANGE_SUCCESS = 171;

    public static final String USER_REVIEW_CHANGE_SUCCESS_MSG = "change review success";

    public static final int USER_REVIEW_CHANGE_ERR = 172;

    public static final String USER_REVIEW_CHANGE_ERR_MSG = "change review fail";

    public static final int USER_RUNTIME_TASK_SUCCESS = 201;

    public static final int USER_RUNTIME_TASK_ERR = 202;

    public static final String USER_RUNTIME_TASK_ERR_MSG = "user runtime task operator error";


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
