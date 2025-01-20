package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;

public interface UsersService {
    /**
     * login
     * @param username
     * @param password
     * @return
     */
    public ServiceResDto login(String username, String password);

    public ServiceResDto register(String username, String password);
}
