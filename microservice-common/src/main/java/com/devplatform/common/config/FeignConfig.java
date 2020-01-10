
package com.devplatform.common.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.devplatform.common.util.ValidateUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignConfig implements RequestInterceptor {
    
    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = getHttpServletRequest();
        if (null != request && !ValidateUtil.isEmpty(request.getHeader("Authorization"))) {
            template.header("Authorization", request.getHeader("Authorization"));
        }
    }
    
    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        catch (Exception e) {
            return null;
        }
    }
}
