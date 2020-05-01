package net.atpco.pi.piuiautomationdata.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiDataMappingRaw {
    private String name;
    private String baseUrl;
    private List<DataCombination> combinations;
}
