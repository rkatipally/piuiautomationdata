package net.atpco.pi.piuiautomationdata.model.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.atpco.pi.pistrategyskipper.enums.QueryStatus;
import net.atpco.pi.pistrategyskipper.enums.SearchType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseSearchStatus {
    private String _id;
    private SearchType searchType;
    private QueryStatus status;
    private String domesticSubsTime;
	private String internationalSubsTime;
}
