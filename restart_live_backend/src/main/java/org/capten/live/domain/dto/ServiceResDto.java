package org.capten.live.domain.dto;

/**
 * return different code and data to controller, to help controller know how to detail service result.
 * service code shouldn't be same with front end code, and service result should be record in logs.
 * @param code
 * @param data
 */
public record ServiceResDto(int code, Object data) {

}
