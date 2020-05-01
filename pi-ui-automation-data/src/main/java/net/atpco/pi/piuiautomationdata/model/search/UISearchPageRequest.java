package net.atpco.pi.piuiautomationdata.model.search;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import net.atpco.pi.pistrategyskipper.enums.SearchType;

@Getter
@Setter
@EqualsAndHashCode
public class UISearchPageRequest {
    private int pageNo;
    private String queryId;
    private int pageSize;
    private SearchType searchType;
    private boolean newInsert;
}
