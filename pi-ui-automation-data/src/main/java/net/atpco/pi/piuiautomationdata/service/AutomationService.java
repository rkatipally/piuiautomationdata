package net.atpco.pi.piuiautomationdata.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.ContentsService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.EMPTY_JSON;
import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.HTTP_GET;
import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.HTTP_POST;
import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.JSON_EXTENSION;
import static net.atpco.pi.piuiautomationdata.constants.AutomationConstants.fieldsToRemove;

import net.atpco.pi.piuiautomationdata.model.ApiDataMapping;
import net.atpco.pi.piuiautomationdata.model.ApiDataMappingRaw;
import net.atpco.pi.piuiautomationdata.model.DataCombination;
import net.atpco.pi.piuiautomationdata.model.TestDataMapping;
import net.atpco.pi.piuiautomationdata.repository.ApiDataMappingRepository;
import net.atpco.pi.piuiautomationdata.repository.TestDataMappingRepository;
import net.atpco.pi.piuiautomationdata.settings.AutomationSettings;
import net.atpco.pi.piuiautomationdata.settings.GitHubSettings;
import net.atpco.pi.piuiautomationdata.util.AutomationUtil;

@Service
@AllArgsConstructor
@Slf4j
public class AutomationService {

    final private AutomationSettings automationSettings;
    final private GitHubSettings gitHubSettings;
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

    public String getResponseForApi(HttpServletRequest request) throws IOException {
        String url = request.getRequestURI();
        ApiDataMapping apiDataMapping = new ApiDataMapping();

        switch (request.getMethod()) {
            case HTTP_GET:
                log.info("Mocking for URL - {}", url);
                apiDataMapping = apiDataMappingRepository.findByUrl(url, automationSettings.getApiDataMappingCollection());
                break;
            case HTTP_POST:
                JSONObject requestJson = objectMapper.readValue(request.getReader(), JSONObject.class);
                AutomationUtil.removeFields(requestJson, fieldsToRemove);
                log.info("Mocking for URL - {}, with request - {}", url, requestJson.toJSONString());
                apiDataMapping = apiDataMappingRepository.findByUrlAndRequest(url, requestJson, automationSettings.getApiDataMappingCollection());

        }
        if (apiDataMapping == null) return EMPTY_JSON;
        return objectMapper.writeValueAsString(apiDataMapping.getResponse());
    }

    public void loadDataFromGitHub() throws IOException {
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(gitHubSettings.getToken());
        ContentsService contentsService = new ContentsService(client);
        List<ApiDataMappingRaw> decodedList = new ArrayList<>();

        RepositoryId repositoryId = new RepositoryId(gitHubSettings.getOwner(), gitHubSettings.getRepo());
        List<RepositoryContents> contents = contentsService.getContents(repositoryId, gitHubSettings.getApiDataMappingPath());
        Queue<RepositoryContents> contentsQueue = new LinkedList<>(contents);
        while (!contentsQueue.isEmpty()) {
            RepositoryContents repositoryContents = contentsQueue.remove();
            if (RepositoryContents.TYPE_FILE.equals(repositoryContents.getType())) {
                if (!StringUtils.endsWithIgnoreCase(repositoryContents.getPath(), JSON_EXTENSION)) continue;
                RepositoryContents fileContent = contentsService.getContents(repositoryId, repositoryContents.getPath()).get(0);
                String decodedContent = new String(Base64.getMimeDecoder().decode(fileContent.getContent()));
                decodedList.addAll(objectMapper.readValue(decodedContent, new TypeReference<List<ApiDataMappingRaw>>() {
                }));
            } else {
                contentsQueue.addAll(contentsService.getContents(repositoryId, repositoryContents.getPath()));
            }
        }
        apiDataMappingRepository.dropCollection(automationSettings.getApiDataMappingCollection());
        this.insertApiDataMapping(decodedList);
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
        this.insertApiDataMapping(rawApiDataMappingList);
    }

    public void insertApiDataMapping(List<ApiDataMappingRaw> rawApiDataMappingList) {
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
        log.info("ApiDataMapping size is - {}", apiDataMappingList.size());
        apiDataMappingRepository.insert(apiDataMappingList, automationSettings.getApiDataMappingCollection());
    }

    @PreDestroy
    public void clearTestData() {
        testDataMappingRepository.dropCollection(automationSettings.getTestDataMappingCollection());
        apiDataMappingRepository.dropCollection(automationSettings.getApiDataMappingCollection());
    }
}
