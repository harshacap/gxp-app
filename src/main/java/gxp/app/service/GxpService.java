package gxp.app.service;

import java.util.List;

import gxp.app.entity.GxpEntity;
import gxp.app.model.GuestModel;

public interface GxpService {

	public void persistGxpPayload(GxpEntity ge, String payLoad);
	
	public GuestModel getGuestDeailsBySwidId(String swidId);

	public void persistGuestDetails(String xid, String identifierType, String identifierValue);

	public boolean getGxpPayLoadDetails(String identifierValue);

	public void updateGxpPayload(GxpEntity ge, String payLoad);

	public List getGxpDetails(String gxpLinkId);

}
