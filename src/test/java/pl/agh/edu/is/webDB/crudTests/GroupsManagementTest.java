package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.GroupsManagement;
import pl.agh.edu.is.webDB.entities.Group;
import pl.agh.edu.is.webDB.entities.Right;


public class GroupsManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		Group obj1 = new Group("inwalidzi", "grua inwalidzka", true);
		Group obj2 = new Group("rudzi", "dla specjalnych uczniow.", true);
		Group obj3 = new Group("elita", "dostep do wszystkiego", false);
		Group obj4 = new Group("inni", "grupa dla ludzi z laboratorium", false);
		Group obj5 = new Group("geeks", "grupa dla naukowcow", true);
		Group obj6 = new Group("loosers", "reszta", true);
		
		Right r1 = new Right("odczyt promocji", "user moze odczytywac wszelkie dostepne promocje");
		Right r2 = new Right("zapis szkod", "user moze zapisywac przygotowane szkody");
		Right r3 = new Right("zmiana ustawien aplikacji", "dostep do panelu admina");
		Right r4 = new Right("odczyt", "tylko odczyt danych");
		Right r5 = new Right("tworzenie konta", "user moze tworzyc konta innym");
		Right r6 = new Right("dodawanie promocji", "user moze dodawac promocje");
		
		ArrayList<Right> rightsSet1 = new ArrayList<Right>();
		ArrayList<Right> rightsSet2 = new ArrayList<Right>();
		ArrayList<Right> rightsSet3 = new ArrayList<Right>();
		
		rightsSet1.add(r1);
		rightsSet1.add(r2);
		rightsSet1.add(r3);
		rightsSet1.add(r4);
		rightsSet2.add(r6);
		rightsSet2.add(r5);
		rightsSet3.add(r5);
		
		obj1.setRights(rightsSet1);
		obj2.setRights(rightsSet1);
		obj3.setRights(rightsSet1);
		obj4.setRights(rightsSet1);
		obj5.setRights(rightsSet2);
		obj6.setRights(rightsSet3);
		
		
		GroupsManagement.add(obj1);
		GroupsManagement.add(obj2);
		GroupsManagement.add(obj3);
		GroupsManagement.add(obj4);
		GroupsManagement.add(obj5);
		GroupsManagement.add(obj6);
		
		ArrayList<Group> Groups = GroupsManagement.getAll();
		for(Group obj:Groups){
			System.out.println(obj);
		}
		
		obj2.setDescription("dla bardzo specjanych");
		GroupsManagement.update(obj2);
		
		System.out.println(GroupsManagement.findById("rudzi"));
		System.out.println(obj2);
		
		assertTrue(obj2.equals(GroupsManagement.findById("rudzi")));
		assertTrue(GroupsManagement.getAll().size() == 6);
		assertFalse(obj2.equals(GroupsManagement.findById("geeks")));
		
		GroupsManagement.delete("loosers");
		assertTrue(GroupsManagement.findById("loosers") == null);
		assertEquals(5, GroupsManagement.getAll().size());
		

//		log.info("Tests finished.");
	}
}
