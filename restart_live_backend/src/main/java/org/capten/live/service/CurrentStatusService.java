package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.CurrentStatus;

import java.util.List;

public interface CurrentStatusService {

    ServiceResDto getCurrentStatus(String header);

    ServiceResDto updateCurrentStatus(String header, List<CurrentStatus> currentStatusList);
}
