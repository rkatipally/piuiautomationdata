package net.atpco.pi.piuiautomationdata.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


import net.atpco.pi.piuiautomationdata.model.TestDataMapping;


@Repository
@AllArgsConstructor
public class TestDataMappingRepositoryImpl implements TestDataMappingRepository {

	@Autowired
    final private MongoTemplate template;

    @Override
	public  List<TestDataMapping> insert(List<TestDataMapping> batchToSave, String collectionName) {
		return (List<TestDataMapping>) template.insert(batchToSave, collectionName);
	}

	@Override
	public  List<TestDataMapping> findAll(Class<TestDataMapping> entityClass, String collectionName) {
		return template.findAll(entityClass, collectionName);
	}

	@Override
	public void dropCollection(String collectionName) {
		if(template.getCollectionNames().contains(collectionName)){
			template.dropCollection(collectionName);
		}
	}

}
