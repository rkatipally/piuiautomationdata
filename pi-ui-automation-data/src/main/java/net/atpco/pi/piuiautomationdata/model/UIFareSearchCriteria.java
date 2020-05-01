package net.atpco.pi.piuiautomationdata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UIFareSearchCriteria implements Serializable {
    private static final long serialVersionUID = 5014583174238891923L;

    private String pdt;
    private String origin;
    private String destination;
    private String market;
    private String carrier;
    private String fareClasses;
    private String cabins;
    private String fareTypeCode;
    private String passengerTypeCode;
    private String footnote;
    private String currency;
    private String rbd;
    private String minStay;
    private String advancedPurchase;
    private String seasonalityType;
    private String seasonality;
    private String accountCode;
    private String owrt;
    private String rule;
    private String routing;
    private String cabin;
    private String itineraryTripType;
    private Map<String, String> marketAttributes;
}
