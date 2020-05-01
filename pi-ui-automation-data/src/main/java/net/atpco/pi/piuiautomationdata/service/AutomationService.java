package net.atpco.pi.piuiautomationdata.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.atpco.pi.piuiautomationdata.settings.AutomationSettings;

@Service
@AllArgsConstructor
public class AutomationService {

    final private AutomationSettings automationSettings;

    public boolean isTestUser(String userId){
        return !StringUtils.isEmpty(userId) && automationSettings.getUsers().contains(userId.toLowerCase());
    }
}
