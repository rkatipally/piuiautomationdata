package net.atpco.pi.piuiautomationdata.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.X_USER_ID;

import net.atpco.pi.piuiautomationdata.model.FareSearchStatus;
import net.atpco.pi.piuiautomationdata.model.UIServiceResponse;
import net.atpco.pi.piuiautomationdata.service.AutomationService;

@Slf4j
public class AutomationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AutomationService automationService;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (automationService.isTestUser(request.getHeader(X_USER_ID))) {
            HandlerMethod method = (HandlerMethod) handler;
            log.info("[preHandle][" + request + "]" + "[" + request.getMethod() + "]" + request.getRequestURI());
            UIServiceResponse<FareSearchStatus, String> modifiedResponse = new UIServiceResponse<>();
            modifiedResponse.setData(new FareSearchStatus());
            modifiedResponse.setMessage("new message");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode requestJson = objectMapper.readTree(request.getReader());
            String jsonStr = requestJson.toPrettyString();
            log.info(jsonStr);
            String modifiedResponseStr = objectMapper.writeValueAsString(modifiedResponse);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(modifiedResponseStr);
            return false;
        }
        return true;
    }
}
