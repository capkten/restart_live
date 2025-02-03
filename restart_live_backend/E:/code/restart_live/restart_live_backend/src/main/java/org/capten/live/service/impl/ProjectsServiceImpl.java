package org.capten.live.service.impl;

import org.capten.live.model.Projects;
import org.capten.live.mapper.ProjectsMapper;
import org.capten.live.service.IProjectsService;
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
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService {

}
