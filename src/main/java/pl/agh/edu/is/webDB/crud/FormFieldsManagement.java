package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.FormField;

public class FormFieldsManagement {

	public static void add(FormField obj) {
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

	public static void update(FormField obj) {
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

	public static void delete(String formFieldName) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from FormField where formFieldName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", formFieldName);
			FormField sr = (FormField) qString.uniqueResult();
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

	public static FormField findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		FormField sr = null;
		try {
			String queryString = "from FormField where formFieldName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", pId);
			sr = (FormField) qString.uniqueResult();
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

	public static ArrayList<FormField> getAll(){
		ArrayList<FormField> res = new ArrayList<FormField>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from FormField";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<FormField>) qString.list();
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
		sb.append("Field Type"+separator);
		sb.append("Description"+separator);
	
		sb.append("Author"+separator);
		sb.append("Time Created"+separator);
		sb.append("Modifier"+separator);
		sb.append("Time Modified");
		
		
		return sb.toString();
	}
	
	public static ArrayList<String> getAllFormFieldsAsListOfSeparatedStrings(String separator){
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<FormField> allFormFields = getAll();
		
		for(FormField ff:allFormFields){
			result.add(ff.getAllDataAsString(separator));
		}
		
		return result;
	}

	
}
