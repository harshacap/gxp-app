package gxp.app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GxpEntity {

	@JsonProperty(value="identifier-type")
	private String identifierType;
	
	@JsonProperty(value="identifier-value")
	private String identifierValue;
	
	@JsonProperty(value="swid")
	private String swid;
	
	@JsonProperty(value="gxp-link-id")
	private String gxpLinkId;
	
	public String getGxpLinkId() {
		return gxpLinkId;
	}

	public void setGxpLinkId(String gxpLinkId) {
		this.gxpLinkId = gxpLinkId;
	}

	public GxpEntity(){
		
	}
	
	public String getSwid() {
		return swid;
	}

	public void setSwid(String swid) {
		this.swid = swid;
	}

	public String getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public String getIdentifierValue() {
		return identifierValue;
	}

	public void setIdentifierValue(String identifierValue) {
		this.identifierValue = identifierValue;
	}
	
}
