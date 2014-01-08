package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.Right;

public class RightsManagement {

	public static void add(Right obj) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			obj.setAuthor("admin");
			obj.setModifier("admin");
			obj.setTimeCreated(Calendar.getInstance().getTimeInMillis());
			obj.setTimeModified(Calendar.getInstance().getTimeInMillis());
			session.save(obj);
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

	public static void update(Right obj) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			obj.setTimeModified(Calendar.getInstance().getTimeInMillis());
			obj.setModifier("admin");
			session.merge(obj);
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

	public static void delete(String RightName) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Right where rightName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", RightName);
			Right obj = (Right) qString.uniqueResult();
			session.delete(obj);
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

	public static Right findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		Right sr = null;
		try {
			String queryString = "from Right where rightName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", pId);
			sr = (Right) qString.uniqueResult();
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

	public static ArrayList<Right> getAll(){
		ArrayList<Right> res = new ArrayList<Right>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from Right";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<Right>) qString.list();
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
	
	/**
	 * Method for obtaining String header for what is present in String returned by {@code getAllDataAsString} method.
	 * @param separator string that should be used to separate values.
	 * @return single String with names of all fields in entity.
	 */
	public static String getStringDataHeader(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append("ID/Name"+separator);
		sb.append("Description"+separator);
	
		sb.append("Author"+separator);
		sb.append("Time Created"+separator);
		sb.append("Modifier"+separator);
		sb.append("Time Modified");
		
		return sb.toString();
	}
	
	public static ArrayList<String> getAllRightsAsListOfSeparatedStrings(String separator){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Right> allRights = getAll();
		
		for(Right r:allRights){
			result.add(r.getAllDataAsString(separator));
		}
		
		return result;
	}

	
}
