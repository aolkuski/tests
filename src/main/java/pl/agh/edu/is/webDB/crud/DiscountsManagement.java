package pl.agh.edu.is.webDB.crud;

import java.util.ArrayList;
import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import pl.agh.edu.is.webDB.entities.Discount;

public class DiscountsManagement {

	public static void add(Discount dsc) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			dsc.setAuthor("admin");
			dsc.setModifier("admin");
			dsc.setTimeCreated(Calendar.getInstance().getTimeInMillis());
			dsc.setTimeModified(Calendar.getInstance().getTimeInMillis());
			session.save(dsc);
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


	public static void update(Discount dsc) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			session.merge(dsc);
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

	public static void delete(String pDiscountName) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();

		try {
			String queryString = "from Discount where discountName = :dscName";
			Query qString = session.createQuery(queryString);
			qString.setString("dscName", pDiscountName);
			Discount app = (Discount) qString.uniqueResult();
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

	public static Discount findById(String pId) {
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		Discount dsc = null;
		try {
			String queryString = "from Discount where discountName = :id";
			Query qString = session.createQuery(queryString);
			qString.setString("id", pId);
			dsc = (Discount) qString.uniqueResult();
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
		return dsc;
	}

	public static ArrayList<Discount> getAll(){
		ArrayList<Discount> res = new ArrayList<Discount>();
		
		SessionFactory sf = HibernateUtils.getSessionFactory();
		Session session = sf.openSession();
		Transaction tn = session.beginTransaction();
		
		try{
			String queryString = "from Discount";
			Query qString = session.createQuery(queryString);
			res =  (ArrayList<Discount>) qString.list();
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
