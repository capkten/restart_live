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
import org.capten.live.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @Value("${static.file.location}")
    private String fileLocation;

    @Value("${static.file.folder.compress}")
    private String compressFolder;

    @Value("${static.baseURL.staticPath}")
    private String staticPath;

    @Value("${static.baseURL}")
    private String baseUrl;

    @Autowired
    private ImageUtil imageUtil;

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
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
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
            users.setUpdateTime(LocalDateTime.now());
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
        if (config == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        return new ServiceResDto(UsersBo.USER_INFO_SUCCESS, config);
    }

    @Override
    public ServiceResDto updateIcon(String icon, String token) {
        String username = usersBo.getUserNameByToken(token);
        if (configDao.setIconByUsername(icon, username)) {
            return getUserConfig(token);
        }else {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
    }

    @Override
    public ServiceResDto updateBackgroundImage(MultipartFile file, String token) {
        //check type
        if (!imageUtil.checkImageType(file)) {
            return new ServiceResDto(UsersBo.USER_CHANGE_BG_FAIL_TYPE_ERR, null);
        }
        if (!imageUtil.checkImageSize(file, 5 * 1024 * 1024)) {
            return new ServiceResDto(UsersBo.USER_CHANGE_BG_FILE_SIZE_ERR, null);
        }
        //save image
        String username = usersBo.getUserNameByToken(token);
        Users users = usersDao.getUserInfoByUserName(username);
        if (users == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        } else {
            String fileName = file.getOriginalFilename();
            String fileFolder = fileLocation + File.separator + users.getId();
            // check file folder is exist
            if (!new File(fileFolder).exists()) {
                new File(fileFolder).mkdirs();
            }
            String filePath = fileLocation + File.separator + users.getId() + File.separator +  fileName;
            String compressFilePath = fileLocation + File.separator + users.getId() + File.separator + compressFolder + File.separator + fileName;
            String fileCompressFolder = fileLocation + File.separator + users.getId() + File.separator + compressFolder;
            if (!new File(fileCompressFolder).exists()) {
                new File(fileCompressFolder).mkdirs();
            }
            try {
                file.transferTo(new File(filePath));
                // 压缩文件
                imageUtil.compressImage(new File(filePath), new File(compressFilePath));
                String httpFilePath = baseUrl + "/" + staticPath + "/" + users.getId() + "/" + compressFolder + "/" + fileName;
                if (configDao.setBgImgByUid(users.getId(), httpFilePath)) {
                    return getUserConfig(token);
                }
                return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}