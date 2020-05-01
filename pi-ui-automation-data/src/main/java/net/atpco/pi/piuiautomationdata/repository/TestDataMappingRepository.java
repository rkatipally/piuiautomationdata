package net.atpco.pi.piuiautomationdata.repository;

import java.util.List;

import net.atpco.pi.piuiautomationdata.model.TestDataMapping;

public interface TestDataMappingRepository {
    List<TestDataMapping> insert(List<TestDataMapping> batchToSave, String collectionName);
    List<TestDataMapping> findAll(Class<TestDataMapping> entityClass, String collectionName);
    void dropCollection(String collectionName);
}
