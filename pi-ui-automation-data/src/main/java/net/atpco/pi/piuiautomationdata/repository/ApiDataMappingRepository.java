package net.atpco.pi.piuiautomationdata.repository;

import org.json.simple.JSONObject;

import java.util.List;

import net.atpco.pi.piuiautomationdata.model.ApiDataMapping;

public interface ApiDataMappingRepository {
    List<ApiDataMapping> insert(List<ApiDataMapping> batchToSave, String collectionName);
    List<ApiDataMapping> findAll(Class<ApiDataMapping> entityClass, String collectionName);
    void dropCollection(String collectionName);
    ApiDataMapping findByUrlAndRequest(String url, JSONObject request, String collectionName);
}
