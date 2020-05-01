package net.atpco.pi.piuiautomationdata.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.IOException;
import java.util.List;

import net.atpco.pi.piuiautomationdata.model.ApiDataMapping;
import net.atpco.pi.piuiautomationdata.model.TestDataMapping;
import net.atpco.pi.piuiautomationdata.repository.ApiDataMappingRepository;
import net.atpco.pi.piuiautomationdata.repository.TestDataMappingRepository;
import net.atpco.pi.piuiautomationdata.settings.AutomationSettings;
import net.atpco.pi.piuiautomationdata.util.AutomationUtil;

@Service
@AllArgsConstructor
@Slf4j
public class AutomationService {

    final private AutomationSettings automationSettings;
    final private TestDataMappingRepository testDataMappingRepository;
    final private ApiDataMappingRepository apiDataMappingRepository;

    @PostConstruct
    public void loadData() {
        try {
            clearTestData();
            loadTestDataMapping();
            loadApiDataMapping();
        } catch (Exception ex) {
            log.error("Error occurred while loading test data ", ex);
        }
    }

    public String getResponseForApi(){
        return null;
    }

    public boolean isTestUser(String userId) {
        return !StringUtils.isEmpty(userId) && automationSettings.getUsers().contains(userId.toLowerCase());
    }

    public void loadTestDataMapping() throws IOException {
        List<TestDataMapping> testDataMappingList = AutomationUtil.readResource(automationSettings.getTestDataMappingPath(), TestDataMapping.class);
        log.info("TestDataMapping size is - {}", testDataMappingList.size());
        testDataMappingRepository.insert(testDataMappingList, automationSettings.getTestDataMappingCollection());
    }

    public void loadApiDataMapping() throws IOException {
        List<ApiDataMapping> apiDataMappingList = AutomationUtil.readResource(automationSettings.getApiDataMappingPath(), ApiDataMapping.class);
        log.info("ApiDataMapping size is - {}", apiDataMappingList.size());
        apiDataMappingRepository.insert(apiDataMappingList, automationSettings.getApiDataMappingCollection());
    }

    @PreDestroy
    public void clearTestData(){
        testDataMappingRepository.dropCollection(automationSettings.getTestDataMappingCollection());
        apiDataMappingRepository.dropCollection(automationSettings.getApiDataMappingCollection());
    }
}
