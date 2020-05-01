package net.atpco.pi.piuiautomationdata.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TestDataMapping {
    private String testId;
    private String queryId;
    private Map<String, String> mapping;
}
