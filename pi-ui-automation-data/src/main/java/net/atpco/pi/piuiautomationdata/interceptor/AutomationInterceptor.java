package net.atpco.pi.piuiautomationdata.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.EMPTY_JSON;
import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.X_USER_ID;

import net.atpco.pi.piuiautomationdata.service.AutomationService;

@Slf4j
public class AutomationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AutomationService automationService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        // Intercept only for test users
        if (automationService.isTestUser(request.getHeader(X_USER_ID))) {
            log.info("preHandle - {} - {} - {}", request, request.getMethod(), request.getRequestURI());
            String responseBody = automationService.getResponseForApi(request);
            // Set error code when no match found
            if(EMPTY_JSON.equals(responseBody)) response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(responseBody);
            return false;
        }
        return true;
    }
}
