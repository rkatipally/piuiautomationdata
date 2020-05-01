package net.atpco.pi.piuiautomationdata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.fieldsToRemove;

import net.atpco.pi.piuiautomationdata.model.ApiDataMapping;
import net.atpco.pi.piuiautomationdata.model.ApiDataMappingRaw;
import net.atpco.pi.piuiautomationdata.model.DataCombination;
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
    final private ObjectMapper objectMapper = new ObjectMapper();

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

    public String getResponseForApi(HttpServletRequest request, HandlerMethod handler) throws IOException {
        JSONObject requestJson = objectMapper.readValue(request.getReader(), JSONObject.class);
        AutomationUtil.removeFields(requestJson, fieldsToRemove);
        String url = request.getRequestURI();
        ApiDataMapping apiDataMapping = apiDataMappingRepository.findByUrlAndRequest(url, requestJson, automationSettings.getApiDataMappingCollection());
        return objectMapper.writeValueAsString(apiDataMapping.getResponse());
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
        List<ApiDataMappingRaw> rawApiDataMappingList = AutomationUtil.readResource(automationSettings.getApiDataMappingPath(), ApiDataMappingRaw.class);
        log.info("ApiDataMapping size is - {}", rawApiDataMappingList.size());
        List<ApiDataMapping> apiDataMappingList = new ArrayList<>();
        for (ApiDataMappingRaw rawApiDataMapping : rawApiDataMappingList) {
            for (DataCombination dataCombination : rawApiDataMapping.getCombinations()) {
                ApiDataMapping apiDataMapping = new ApiDataMapping();
                AutomationUtil.removeFields(dataCombination.getRequest(), fieldsToRemove);
                BeanUtils.copyProperties(dataCombination, apiDataMapping);
                BeanUtils.copyProperties(rawApiDataMapping, apiDataMapping);
                apiDataMapping.setUrl(rawApiDataMapping.getBaseUrl() + dataCombination.getUrlStr());
                apiDataMappingList.add(apiDataMapping);
            }
        }
        apiDataMappingRepository.insert(apiDataMappingList, automationSettings.getApiDataMappingCollection());
    }

    @PreDestroy
    public void clearTestData() {
        testDataMappingRepository.dropCollection(automationSettings.getTestDataMappingCollection());
        apiDataMappingRepository.dropCollection(automationSettings.getApiDataMappingCollection());
    }
}
