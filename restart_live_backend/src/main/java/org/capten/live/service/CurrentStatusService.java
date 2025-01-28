package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;

public interface CurrentStatusService {

    ServiceResDto getCurrentStatus(String header);
}
