package org.capten.live.domain.vo;

public record ResponseVo(Integer code, String msg, Object data) {

    /**
     * return data to front end with data and code 200
     * @param data
     * @return ResponseVo
     */
    public static ResponseVo success(Object data) {
        return new ResponseVo(200, "success", data);
    }

    /**
     * return data to front end without data and return code 200
     * @return
     */
    public static ResponseVo success() {
        return new ResponseVo(200, "success", null);
    }

    /**
     * return error message to front end with code 500 and msg
     * @param msg
     * @return
     */
    public static ResponseVo error(String msg) {
        return new ResponseVo(500, msg, null);
    }

    public static ResponseVo tokenError() {
        return new ResponseVo(401, "token is invalid or expired", null);
    }

}
