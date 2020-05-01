package net.atpco.pi.piuiautomationdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import net.atpco.pi.pistrategyskipper.enums.QueryStatus;
import net.atpco.pi.pistrategyskipper.enums.SearchType;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FareSearchStatus extends BaseSearchStatus {
    private int totalFareCount;
    private int totalRequestCount;
    private int completedRequestCount;
    private int errorCount;
    private String hostCarrier;
    private boolean maxMarketLimitReached;
    @JsonIgnore
    private List<WorkingFareSearchRequest> searchRequests = new ArrayList<>();

    @Builder
    public FareSearchStatus(String _id, SearchType searchType, QueryStatus status, int totalFareCount, int totalRequestCount,
                            int completedRequestCount, int errorCount, String domesticSubsTime, String internationalSubsTime,
                            String hostCarrier, boolean maxMarketLimitReached, List<WorkingFareSearchRequest> requests) {
        super(_id, searchType, status, domesticSubsTime, internationalSubsTime);
        this.totalFareCount = totalFareCount;
        this.totalRequestCount = totalRequestCount;
        this.completedRequestCount = completedRequestCount;
        this.errorCount = errorCount;
        this.hostCarrier = hostCarrier;
        this.maxMarketLimitReached = maxMarketLimitReached;
        this.searchRequests = requests;
    }
}