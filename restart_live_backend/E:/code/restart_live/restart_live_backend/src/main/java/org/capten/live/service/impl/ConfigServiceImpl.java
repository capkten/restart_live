package org.capten.live.service.impl;

import org.capten.live.model.Config;
import org.capten.live.mapper.ConfigMapper;
import org.capten.live.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
