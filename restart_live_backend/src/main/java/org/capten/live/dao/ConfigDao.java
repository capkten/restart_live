package org.capten.live.dao;

import com.github.yulichang.query.MPJLambdaQueryWrapper;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.capten.live.model.Config;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.capten.live.mapper.ConfigMapper;

import java.util.List;

@Component
public class ConfigDao {

    @Autowired
    private ConfigMapper configMapper;

    @Value("${static.baseURL}")
    private String baseUrl;

    @Value("${static.default.bg_img}")
    private String defaultTopBgImg;

    @Value("${static.default.icon}")
    private String defaultTopBgIcon;

    public boolean createBaseConfig(Integer id) {
        Config config = new Config();
        config.setUid(id);
        config.setTopBgImg(baseUrl + defaultTopBgImg);
        config.setTopBgIcon(defaultTopBgIcon);
        return configMapper.insert(config) > 0;
    }

    public Config getConfigByUserName(String username) {
        Config config = configMapper.selectJoinOne(Config.class,
                new MPJLambdaWrapper<Config>()
                        .selectAll(Config.class)
                        .leftJoin(Users.class, Users::getId, Config::getUid)
                        .eq(Users::getUsername, username));
        return config;
    }

    public boolean setIconByUsername(String icon, String username) {
        Config config = new Config();
        config.setTopBgIcon(icon);
        int i = configMapper.updateJoin(config, new MPJLambdaWrapper<Config>()
                .leftJoin(Config.class, Config::getUid, Users::getId)
                .eq(Users::getUsername, username));
        return i > 0;
    }

    public boolean setBgImgByUid(Integer uid, String httpFilePath) {
        Config config = new Config();
        config.setTopBgImg(httpFilePath);
        config.setUid(uid);
        int i = configMapper.updateById(config);
        return i > 0;
    }
}