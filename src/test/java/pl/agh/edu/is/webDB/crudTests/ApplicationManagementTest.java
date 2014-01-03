package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.ApplicationsManagement;
import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.FormField;

public class ApplicationManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		ArrayList<FormField> fields = new ArrayList<FormField>();
		fields.add(new FormField("name", "typ", "description"));
		fields.add(new FormField("name1", "typ1", "description1"));
		fields.add(new FormField("name2", "typ2", "description2"));
		Application app1 = new Application(1, "new Application", "poszkodowany", "policja", "opis1 1 1", fields);
		Application app2 = new Application(2, "another Application", "thief", "police", "descr2 2 22", fields);
		Application app3 = new Application(3, "last application", "anobody", "gov", "description 3", fields);
		
		ApplicationsManagement.add(app1);
		ApplicationsManagement.add(app2);
		ApplicationsManagement.add(app3);

		Application appDb = ApplicationsManagement.findById("2");

		assertTrue(app2.equals(appDb));
		assertFalse(app1.equals(appDb));
		
		appDb.setApplicant("whoever");
		ApplicationsManagement.update(appDb);
		ApplicationsManagement.update(app3);
		
		appDb = ApplicationsManagement.findById("2");
		
		assertFalse(app2.equals(appDb));
		
		Application app3i = ApplicationsManagement.findById("3");
		System.out.println(app3);
		System.out.println(app3i);
		assertTrue(app3.equals(app3i));
		
//		log.info("Tests finished.");
		
	}
}
