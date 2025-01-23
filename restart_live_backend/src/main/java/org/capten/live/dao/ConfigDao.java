package org.capten.live.dao;

import org.capten.live.model.Config;
import org.capten.live.model.ConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.capten.live.mapper.ConfigMapper;

import java.util.List;
import java.util.UUID;

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

    public boolean createBaseConfig(Object id) {
        Config config = new Config();
        config.setId(UUID.randomUUID());
        config.setUid(id);
        config.setTopBgImg(baseUrl + defaultTopBgImg);
        config.setTopBgIcon(defaultTopBgIcon);
        return configMapper.insert(config) > 0;
    }

    public Config getConfigByUserName(String username) {
        return configMapper.getConfigByUserName(username);
    }

    public boolean setIconByUsername(String icon, String username) {
        return configMapper.updateIconByUsername(icon, username) > 0;
    }

    public boolean setBgImgByUid(Object uid, String httpFilePath) {
        Config config = new Config();
        config.setTopBgImg(httpFilePath);
        ConfigExample configExample = new ConfigExample();
        configExample.createCriteria().andUidEqualTo(uid);
        return configMapper.updateByExampleSelective(config, configExample) > 0;
    }
}