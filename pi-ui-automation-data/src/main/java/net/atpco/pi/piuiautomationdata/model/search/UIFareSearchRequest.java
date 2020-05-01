package net.atpco.pi.piuiautomationdata.model.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import net.atpco.pi.pistrategyskipper.enums.SearchType;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UIFareSearchRequest {
    private SearchType searchType;
    private String userId;
    private String queryId;
    private String effectiveDate;
    private String firstTravelDate;
    private boolean yqyr;

    private List<UIFareSearchCriteria> criteria;
    private String hostCarrier;
}
