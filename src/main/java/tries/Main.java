package tries;

import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import pl.agh.edu.is.webDB.entities.Discount;

public class Main {

	public static void main(String[] args) {

		Configuration cfg = new Configuration();
		File f = new File("");
		System.out.println(f.getAbsolutePath());
//		cfg.configure(f.getAbsolutePath()+"\\src\\main\\java\\resources\\hibernate.cfg.xml");
		cfg.configure("hibernate.cfg.xml");
		// creating seession factory object
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		SessionFactory factory = cfg.buildSessionFactory();
		// creating session object
		Session session = factory.openSession();
		// creating transaction object
		
		Transaction tx = null;
		try{
	         tx = session.beginTransaction();
	         Discount dsc = new Discount("nazwa", 1.01, "typ", "jednostka",1, 2, "wiek", true);
	         session.save(dsc); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }

	}
}
