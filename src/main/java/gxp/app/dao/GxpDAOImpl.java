package gxp.app.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import gxp.app.model.GuestModel;
import gxp.app.model.GxpModel;


@Repository
public class GxpDAOImpl implements GxpDAO {
	
	@Autowired
	@Qualifier("hibernate4AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void persistGxpPayload(GxpModel gxpModel) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(gxpModel);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public GuestModel getGuestDetailBySwidId(String swidId) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(GuestModel.class);
			cr.add(Restrictions.eq("guestIdValue", swidId));
			return (GuestModel) cr.list().get(0);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void persistGuestDetails(GuestModel gm) {
	
		try{
			Session session =this.sessionFactory.getCurrentSession();
			session.persist(gm);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public boolean getGxpPayLoadDetails(String cannonicalId) {
		boolean gxpflag = false;
		try{
			Session session =this.sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(GxpModel.class);
			cr.add(Restrictions.eq("canonnicalId", cannonicalId));
			if(cr.list().size()>0){
				gxpflag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return gxpflag;
	}

	@Override
	public void updateGxpPayload(GxpModel gm) {
		
		try{
			Session session =this.sessionFactory.getCurrentSession();
			session.update(gm);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List getGxpDetails(String gxpLinkId) {
 
		List gxplist = new ArrayList();
		try {
			Session session = this.sessionFactory.getCurrentSession();		
			String[] a = gxpLinkId.split(",");		
			for(int i=0;i<a.length;i++)
			{
				Criteria cr = session.createCriteria(GxpModel.class);
				String b =a[i].trim();
		
			cr.add(Restrictions.eq("gxpLinkId",b));
			List<GxpModel> evtModel = cr.list();
			for (Iterator iterator = evtModel.iterator(); iterator.hasNext();) {
				GxpModel ev = (GxpModel) iterator.next();
				gxplist.add(ev.getCanonnicalData());				
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return gxplist;
	}

	
	
}
