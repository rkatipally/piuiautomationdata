package net.atpco.pi.piuiautomationdata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class WorkingFareSearchRequest implements Serializable {
	private static final long serialVersionUID = -4981229601600910305L;
	
	private int requestNumber;
	private String origin;
	private String destination;
	private String carrier;
	private String productType;
	private String oneWayRoundtripIndicator;
	private String fareClass;
	private String footNote;
	private String ruleNumber;
	private String routingNumber;
	private String currency;
	
}
