package gxp.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GUEST_KEYS")
public class GuestModel {

	
	@Column(name="XID")
	private String xid;
	
	@Column(name="GUEST_ID_TYPE")
	private String guestIdType;
	
	@Id
	@Column(name="GUEST_ID_VALUE")
	private String guestIdValue;
	
	public GuestModel(){
		
	}
	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}
	public String getGuestIdType() {
		return guestIdType;
	}
	public void setGuestIdType(String guestIdType) {
		this.guestIdType = guestIdType;
	}
	public String getGuestIdValue() {
		return guestIdValue;
	}
	public void setGuestIdValue(String guestIdValue) {
		this.guestIdValue = guestIdValue;
	}
}
