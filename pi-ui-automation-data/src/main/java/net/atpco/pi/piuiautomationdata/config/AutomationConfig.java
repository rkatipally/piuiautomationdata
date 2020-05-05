package net.atpco.pi.piuiautomationdata.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.atpco.pi.piuiautomationdata.interceptor.AutomationInterceptor;
import net.atpco.pi.piuiautomationdata.settings.AutomationSettings;
import net.atpco.pi.piuiautomationdata.settings.GitHubSettings;

@Configuration
@EnableConfigurationProperties({AutomationSettings.class, GitHubSettings.class})
public class AutomationConfig implements WebMvcConfigurer {

    @Bean
    public AutomationInterceptor automationInterceptor(){
        return new AutomationInterceptor();
    }
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(automationInterceptor());
    }
}
