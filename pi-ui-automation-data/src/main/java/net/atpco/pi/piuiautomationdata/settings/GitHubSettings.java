package net.atpco.pi.piuiautomationdata.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="github")
@Getter
@Setter
public class GitHubSettings {
    private String owner;
    private String repo;
    private String apiDataMappingPath;
    private String testDataMappingPath;
    private String token;
}
