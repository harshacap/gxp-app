package gxp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gxp.app.dao.GxpDAO;
import gxp.app.entity.GxpEntity;
import gxp.app.model.GuestModel;
import gxp.app.model.GxpModel;

@Service
public class GxpServiceImpl implements GxpService{
	
	@Autowired
	private GxpDAO gxpDAO;
	
	@Override
	@Transactional("transactionManager")
	public void persistGxpPayload(GxpEntity ge,String payLoad) {
		try{
			GxpModel gm = new GxpModel();
			gm.setCanonnicalId(ge.getIdentifierValue());
			gm.setCanonnicalData(payLoad);
			gm.setGxpLinkId(ge.getGxpLinkId());
			
//			boolean flag = gxpDAO.getGxpPayLoadDetails(gm.getCanonnicalId());
//			if(!flag){
				gxpDAO.persistGxpPayload(gm);
//			}else{
//				gxpDAO.updateGxpPayload(gm);
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	@Transactional("transactionManager")
	public GuestModel getGuestDeailsBySwidId(String swidId) {
		try{
			return gxpDAO.getGuestDetailBySwidId(swidId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional("transactionManager")
	public void persistGuestDetails(String xid, String identifierType, String identifierValue) {
		try{
			GuestModel gm = new GuestModel();
			gm.setXid(xid);
			gm.setGuestIdType(identifierType);
			gm.setGuestIdValue(identifierValue);
			
			gxpDAO.persistGuestDetails(gm);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	@Transactional("transactionManager")
	public boolean getGxpPayLoadDetails(String cannonicalId) {
		boolean flag = false;
		try{
			flag = gxpDAO.getGxpPayLoadDetails(cannonicalId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	@Transactional("transactionManager")
	public void updateGxpPayload(GxpEntity ge, String payLoad) {
		try{
			GxpModel gm = new GxpModel();
			gm.setCanonnicalId(ge.getIdentifierValue());
			gm.setCanonnicalData(payLoad);
			gm.setGxpLinkId(ge.getGxpLinkId());
			
			gxpDAO.updateGxpPayload(gm);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	@Transactional(value="transactionManager")
	public List getGxpDetails(String gxpLinkId) {
		
		return gxpDAO.getGxpDetails(gxpLinkId);
	}
}
