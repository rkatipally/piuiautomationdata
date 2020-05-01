package net.atpco.pi.piuiautomationdata.model.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import net.atpco.common.domain.model.Message;
import net.atpco.pi.pistrategyskipper.enums.SearchType;


@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FareSearchResponse {
    @JsonProperty("queryId")
    private String queryId;
    private String userId;
    private SearchType searchType;

    private int requestNo;
	private int fareCount;
	private int faresWithItinYqyr;
	private String status;
	private Date domesticSubsTime;
	private Date internationalSubsTime;
	private int streamResponseNo;
	private boolean isHostFare;
	
    @JsonProperty("mergedFares")
    private List<PIMergedFare> mergedFares;
    private Message message;
}
