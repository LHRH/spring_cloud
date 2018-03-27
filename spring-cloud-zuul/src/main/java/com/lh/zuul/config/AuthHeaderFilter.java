package com.lh.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @Description:
 * @Date: 2018/3/23 0023 10:40
 */
@Component
public class AuthHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader("X-AUTH-ID",authentication.getPrincipal().toString());
        return null;
    }
}
