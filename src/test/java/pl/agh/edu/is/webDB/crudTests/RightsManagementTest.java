package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.RightsManagement;
import pl.agh.edu.is.webDB.entities.Right;

public class RightsManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		int startSize = RightsManagement.getAll().size();
		
		Right obj1 = new Right("odczyt promocji1", "user moze odczytywac wszelkie dostepne promocje");
		Right obj2 = new Right("zapis szkod1", "user moze zapisywac przygotowane szkody");
		Right obj3 = new Right("zmiana ustawien aplikacji1", "dostep do panelu admina");
		Right obj4 = new Right("odczyt1", "tylko odczyt danych");
		Right obj5 = new Right("tworzenie konta1", "user moze tworzyc konta innym");
		Right obj6 = new Right("dodawanie promocji1", "user moze dodawac promocje");
		
		
		RightsManagement.add(obj1);
		RightsManagement.add(obj2);
		RightsManagement.add(obj3);
		RightsManagement.add(obj4);
		RightsManagement.add(obj5);
		RightsManagement.add(obj6);
		
		
		ArrayList<Right> Rights = RightsManagement.getAll();
		for(Right obj:Rights){
			System.out.println(obj);
		}
		
		obj2.setDescription("user moze to co kazdy!");
		RightsManagement.update(obj2);
		
		System.out.println(RightsManagement.findById("zapis szkod1"));
		System.out.println(obj2);
		
		assertTrue(obj2.equals(RightsManagement.findById("zapis szkod1")));
		assertTrue(RightsManagement.getAll().size() - startSize == 6);
		assertNotSame(obj2, RightsManagement.findById("3"));
		
		RightsManagement.delete("tworzenie konta1");
		assertTrue(RightsManagement.findById("tworzenie konta1") == null);
		assertEquals(5, RightsManagement.getAll().size() - startSize);
		
//		log.info("Tests finished.");

		
	}
}
