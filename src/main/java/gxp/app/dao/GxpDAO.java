package gxp.app.dao;

import java.util.List;

import gxp.app.model.GuestModel;
import gxp.app.model.GxpModel;

public interface GxpDAO {

	public void persistGxpPayload(GxpModel gxpModel);


	public GuestModel getGuestDetailBySwidId(String swidId);

	public void persistGuestDetails(GuestModel gm);

	public boolean getGxpPayLoadDetails(String gxpLinkId);

	public void updateGxpPayload(GxpModel gm);


	public List getGxpDetails(String gxpLinkId);
}
