package net.atpco.pi.piuiautomationdata.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PIMergedFare implements Serializable {
    private static final long serialVersionUID = 7800138294459101423L;

    private String _id;
    private String origin;
    private String originCity;
    private String destination;
    private String destinationCity;
    private String originCountry;
    private String destinationCountry;
    private String carrier;
    private String odc;
    private String productType;
    private String amount;
    private double amountToCompare;
    private String currency;
    private int decimals;
    private String fareClass;
    private String footNote;
    private String globalIndicator;
    private String oneWayRoundtripIndicator;
    private String oneWayAmount;
    private String roundTripAmount;
    private double oneWayAmountToCompare;
    private double roundTripAmountToCompare;
    private String ruleNumber;
    private String sequenceNumber;
    private String tariffNumber;
    private double tariffNumberToCompare;
    private String effectiveDate;
    private String discontinueDate;
    private Date effectiveDateToCompare;
    private Date discontinueDateToCompare;
    private String subsDate;
    private String subsTime;
    private Date subsDateToCompare;
    private int subsTimeToCompare;
    private String fareTypeCode;
    private String passengerType;
    private String passengerTypeIndicator;
    private String primeRBD;
    private String accountCode;
    private String routingNumber;
    private boolean isDomestic;
    private boolean privateFare;
    private String constructionIndicator;

    private double totalAmtToCompare;

    private String baseFareClass;
    private String calculationMethod;

    private String productTypeDesc;
    private String tripDesc;
    private String constructionIndicatorDesc;
    private String refundTypeDesc;
    private String calculationMethodDesc;
    private String globalDesc;

    private Set<Integer> allGenRuleCategoryNumbers;

    private String lastTravelDate;
    private String firstTravelDate;

    private String seasonalityStart;
    private String seasonalityStop;

    private String seasonalityOutbound;
    private String seasonalityInbound;

    private String blackoutOutDates;
    private String blackoutInDates;

    private String firstSaleDate;
    private String lastSaleDate;

    private String dowOutbound;
    private String dowInbound;
    private String visualIndicator;

    @JsonIgnore private String limited;

    private String nonRefundable;
    private String advancePurchase;
    private String minimumStay;
    private String maximumStay;

    private String plus;

    private String basePlusYqYr;
    private double basePlusYqYrToCompare;

    private String seasonalityType;
    private String seasonalityTypeDesc;

    // Total Price by Date
    private String outFareClass;
    private String inFareClass;
    private String outDate;
    private Date outDateToCompare;
    private String inDate;
    private Date inDateToCompare;
    private String[] bookingCode;


    // Total price
    private String baseAmount;
    private double baseAmountToCompare;
    private String surchargeAmount;
    private double surchargeAmountToCompare;
    private String yqyrAmount;
    private double yqyrAmountToCompare;
    private String taxesAmount;
    private double taxesAmountToCompare;
    private String totalAmount;
    private double totalAmountToCompare;
    private String totalAmountWithoutTaxes;
    private double totalAmountWithoutTaxesToCompare;
    private int decimal;

    private String connections;
    private String cabinType;

    @JsonIgnore private BigDecimal outBaseFare;
    @JsonIgnore private BigDecimal inBaseFare;
    private String outBoundRoute;
    private String inBoundRoute;

    // filtering columns
    private Date firstTravelDateToCompare;
    private Date lastTravelDateToCompare;
    private Date firstSaleDateToCompare;
    private Date lastSaleDateToCompare;

    //TotalPriceComparision
    private String diffIndicator;
    private String fareDiffAmt;
    private String fareDiffPct;
    private List<PIMergedFare> tpCompareFares = new ArrayList<>();
    private boolean isHostFare;
    private String outOrigin;
    private String outVia1;
    private String outVia2;
    private String outDestination;
    private String inOrigin;
    private String inVia1;
    private String inVia2;
    private String inDestination;
    private String fan;
    private String fanDate;
    private Date fanDateToCompare;
    private String actionCode;

    //Change Monitoring
    private String workUnit;
    private String receivedDate;
    private String tariffCode;
    private String filingTime;
    private String destCountry;
    private String linkNumber;
    
	// Temporary columns for domestic which includes base amount plus tax
	// For domestic fares this is base amount including tax and
	// for international this is just base amount which is equal to "amount"
	private String taxAmount;
	private double taxAmountToCompare;
	private String owAmtWithTax;
	private double owAmtWithTaxToCompare;
	private String rtAmtWithTax;
	private double rtAmtWithTaxToCompare;

    //Market Attributes
    private Map<String, String> marketAttributes;
    
}

