package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.FinalAim;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
public interface IFinalAimService extends IService<FinalAim> {

     ServiceResDto getAll(String token);

     ServiceResDto change(List<FinalAim> finalAims, String token);
}
