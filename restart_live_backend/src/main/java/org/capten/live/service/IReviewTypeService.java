package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.ReviewType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
public interface IReviewTypeService extends IService<ReviewType> {

    ServiceResDto getAll(String token);

    ServiceResDto add(String name,Integer sort, String token);

    ServiceResDto delete(Integer id, String token);

    ServiceResDto changeName(Integer id, String name, String token);

}
