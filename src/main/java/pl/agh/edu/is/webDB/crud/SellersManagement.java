package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.Seller;

public class SellersManagement {

	public static void add(Seller sr) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			sr.setAuthor("admin");
			sr.setModifier("admin");
			sr.setTimeCreated(Calendar.getInstance().getTimeInMillis());
			sr.setTimeModified(Calendar.getInstance().getTimeInMillis());
			session.save(sr);
			tn.commit();
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}

		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	public static void remove(int entityId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Seller where entityId = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", entityId);
			Seller sr = (Seller) qString.uniqueResult();
			session.delete(sr);
			tn.commit();
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}

		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	public static void update(Seller sr) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Seller where entityId = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", sr.getEntityId());
			Seller srDb = (Seller) qString.uniqueResult();
			if((sr.getAddress() != null) && sr.getAddress() != "") srDb.setAddress(sr.getAddress());
			if((sr.getDescription() != null) && (sr.getDescription() != "")) srDb.setDescription(sr.getDescription());
			if((sr.getType() != null) && (sr.getType() != "")) srDb.setType(sr.getType());
			srDb.setTimeModified(Calendar.getInstance().getTimeInMillis());
			srDb.setModifier("admin");
			session.merge(sr);
			tn.commit();
			
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}

		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
	}

	public static void delete(String pEntityId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Seller where entityId = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", Integer.valueOf(pEntityId));
			Seller sr = (Seller) qString.uniqueResult();
			session.delete(sr);
			tn.commit();
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}

		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}

	}

	public static Seller findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		Seller sr = null;
		try {
			String queryString = "from Seller where entityId = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", Integer.valueOf(pId));
			sr = (Seller) qString.uniqueResult();
			tn.commit();
		} catch (RuntimeException e) {
			if (tn != null) {
				tn.rollback();
			}

		} finally {
			if (session != null) {
				session.flush();
				session.close();
			}
		}
		return sr;
	}

	public static ArrayList<Seller> getAll(){
		ArrayList<Seller> res = new ArrayList<Seller>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from Seller";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<Seller>) qString.list();
			tn.commit();
		}  catch(RuntimeException e){
			if(tn != null){
				tn.rollback();
			}
			
		} finally {
			if (session != null){
				session.flush();
				session.close();
			}
		}
		
		return res;
	}

}
