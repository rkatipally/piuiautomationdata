package net.atpco.pi.piuiautomationdata.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UIServiceResponse<T, R> {
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private R message;
}
