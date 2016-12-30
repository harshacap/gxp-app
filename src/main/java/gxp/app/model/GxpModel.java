package gxp.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name="FASTPASS_PAYLOAD")
public class GxpModel {

	@Id
	@Column(name="CANONNICALID")
	private String canonnicalId;
	
	@Lob
	@Column(name="CANONNICALDATA")
	private String canonnicalData;
	
	@Column(name="GXP_LINK_ID")
	private String gxpLinkId;
	
	public GxpModel(){
		
	}
	
	public String getGxpLinkId() {
		return gxpLinkId;
	}

	public void setGxpLinkId(String gxpLinkId) {
		this.gxpLinkId = gxpLinkId;
	}

	public String getCanonnicalId() {
		return canonnicalId;
	}
	public void setCanonnicalId(String canonnicalId) {
		this.canonnicalId = canonnicalId;
	}
	
	public String getCanonnicalData() {
		return canonnicalData;
	}
	public void setCanonnicalData(String canonnicalData) {
		this.canonnicalData = canonnicalData;
	}
	
}
