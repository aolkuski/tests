package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.Right;

public class ApplicationsManagement {

	public static void add(Application app) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			app.setAuthor("admin");
			app.setModifier("admin");
			app.setTimeCreated(Calendar.getInstance().getTimeInMillis());
			app.setTimeModified(Calendar.getInstance().getTimeInMillis());
			session.save(app);
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


	public static void update(Application pApp) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Application where id = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", pApp.getId());
			Application app = (Application) qString.uniqueResult();
			if((pApp.getApplicant() != null) && (pApp.getApplicant().length() > 0)) app.setApplicant(pApp.getApplicant());
			if((pApp.getDescription() != null) && (pApp.getDescription().length() > 0)) app.setDescription(pApp.getDescription());
			if((pApp.getFormFields().size() > 0)) app.setFormFields(pApp.getFormFields());
			if((pApp.getModifier() != null) && (pApp.getModifier().length() > 0)) app.setModifier(pApp.getModifier());
			if((pApp.getSupervisor() != null) && (pApp.getSupervisor().length() > 0)) app.setSupervisor(pApp.getSupervisor());
			if((pApp.getType() != null) && (pApp.getType().length() > 0)) app.setType(pApp.getType());
			app.setTimeModified(Calendar.getInstance().getTimeInMillis());
			app.setModifier("admin");
			session.merge(app);
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

	public static void delete(String applicationId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Application where id = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", Integer.valueOf(applicationId));
			Application app = (Application) qString.uniqueResult();
			session.delete(app);
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

	public static Application findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		Application app = null;
		try {
			String queryString = "from Application where id = :id";
			Query qString = session.createQuery(queryString);
			qString.setInteger("id", Integer.valueOf(pId));
			app = (Application) qString.uniqueResult();
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
		return app;
	}

	public static ArrayList<Application> getAll(){
		ArrayList<Application> res = new ArrayList<Application>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from Application";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<Application>) qString.list();
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
		sb.append("ID"+separator);
		sb.append("Type"+separator);
		sb.append("Applicant"+separator);
		sb.append("Supervisor"+separator);
		sb.append("Description"+separator);
		sb.append("Fields' names"+separator);
		sb.append("Author"+separator);
		sb.append("Time Created"+separator);
		sb.append("Modifier"+separator);
		sb.append("Time Modified");
		
		return sb.toString();
	}
	
	public static ArrayList<String> getAllApplicationsAsListOfSeparatedStrings(String separator, String secondLevelSeparator){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Application> allApplications = getAll();
		
		for(Application a:allApplications){
			result.add(a.getAllDataAsString(separator, secondLevelSeparator));
		}
		
		return result;
	}

}
