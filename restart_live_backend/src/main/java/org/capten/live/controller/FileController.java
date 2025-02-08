package org.capten.live.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.model.Users;
import org.capten.live.service.UsersService;
import org.capten.live.service.bo.UsersBo;
import org.capten.live.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Value("${static.file.location}")
    private String fileLocation;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersBo usersBo;

    @Value("${static.file.folder.compress}")
    private String compressFolder;

    @Value("${static.baseURL.staticPath}")
    private String staticPath;

    @Value("${static.baseURL}")
    private String baseUrl;

    @Autowired
    private ImageUtil imageUtil;

    @PostMapping("/upload")
    public ResponseVo upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        ServiceResDto serviceResDto = usersService.getUserInfo(request.getHeader(UsersBo.TOKEN_MSG));
        switch (serviceResDto.code()) {
            case UsersBo.USER_NOT_FOUND -> {
                return ResponseVo.error(UsersBo.LOGIN_USER_NOT_FOUND_MSG);
            }
            default -> {
                break;
            }
        }
        Users users = (Users) serviceResDto.data();
        String fileName = file.getOriginalFilename();
        String fileFolder = fileLocation + File.separator + users.getId();
        // check file folder is exist
        if (!new File(fileFolder).exists()) {
            new File(fileFolder).mkdirs();
        }
        String filePath = fileLocation + File.separator + users.getId() + File.separator + fileName;
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
            HashMap<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.put("url", httpFilePath);
            return ResponseVo.success(stringStringHashMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
