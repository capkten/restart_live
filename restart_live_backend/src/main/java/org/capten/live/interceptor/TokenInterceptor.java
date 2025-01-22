package org.capten.live.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.capten.live.annotation.SkipTokenValidation;
import org.capten.live.domain.vo.ResponseVo;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Logger;

@Component
@AllArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersBo usersBo;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger log = Logger.getLogger(TokenInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            SkipTokenValidation skipTokenValidation = handlerMethod.getMethodAnnotation(SkipTokenValidation.class);
            if (skipTokenValidation != null) {
                return true; // Skip token validation
            }
        } else if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
            return true; // Skip token validation for static resources
        }

        String token = request.getHeader("Authorization");
        log.info("token: " + token);
        if (token == null || !isValidToken(token)) {
            ResponseVo tokenIsInvalidOrExpired = ResponseVo.tokenError();
            response.getWriter().write(objectMapper.writeValueAsString(tokenIsInvalidOrExpired));
            return false;
        }
        return true;
    }

    private boolean isValidToken(String token) {
        // Implement your token validation logic here
        return usersBo.checkToken(token);
    }
}