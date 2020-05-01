package net.atpco.pi.piuiautomationdata.model;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

@Getter
@Setter
public class ApiDataMapping {
    private String name;
    private String url;
    private String id;
    private JSONObject request;
    private JSONObject response;

}
