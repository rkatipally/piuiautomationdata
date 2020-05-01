package net.atpco.pi.piuiautomationdata.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix="automation")
@Getter
@Setter
public class AutomationSettings {
    private List<String> users;
}
