package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.Group;

public class GroupsManagement {

	public static void add(Group obj) {
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

	public static void update(Group obj) {
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

	public static void delete(String GroupName) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Group where groupName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", GroupName);
			Group obj = (Group) qString.uniqueResult();
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

	public static Group findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		Group sr = null;
		try {
			String queryString = "from Group where groupName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", pId);
			sr = (Group) qString.uniqueResult();
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

	public static ArrayList<Group> getAll(){
		ArrayList<Group> res = new ArrayList<Group>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from Group";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<Group>) qString.list();
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
