package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.FormFieldsManagement;
import pl.agh.edu.is.webDB.entities.FormField;

public class FormFieldsManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		int startSize = FormFieldsManagement.getAll().size();
		
		FormField obj1 = new FormField("imie", "String", "pole dotyczace imienia");
		FormField obj2 = new FormField("nazwisko", "String", "pole dotyczace nazwiska");
		FormField obj3 = new FormField("ur.", "String", "pole dotyczace daty urodzenia");
		FormField obj4 = new FormField("PESEL", "String", "pole zawierajace numer pesel");
		FormField obj5 = new FormField("tel kom", "Long(9)", "pole na numer telefonu komorkowego - bez spacji ani znakow specjalnych");
		FormField obj6 = new FormField("komentarz", "String", "pole na komentarze");

		FormFieldsManagement.add(obj1);
		FormFieldsManagement.add(obj2);
		FormFieldsManagement.add(obj3);
		FormFieldsManagement.add(obj4);
		FormFieldsManagement.add(obj5);
		FormFieldsManagement.add(obj6);
		
		ArrayList<FormField> formFields = FormFieldsManagement.getAll();
		for(FormField obj:formFields){
			System.out.println(obj);
		}
		
		obj2.setDescription("nazwisko panienskie matki");
		FormFieldsManagement.update(obj2);
		
		System.out.println(FormFieldsManagement.findById("nazwisko"));
		System.out.println(obj2);
		
		assertTrue(obj2.equals(FormFieldsManagement.findById("nazwisko")));
		assertTrue(FormFieldsManagement.getAll().size() - startSize == 6);
		assertNotSame(obj2, FormFieldsManagement.findById("3"));
		
		FormFieldsManagement.delete("komentarz");
		assertTrue(FormFieldsManagement.findById("komentarz") == null);
		assertEquals(5, FormFieldsManagement.getAll().size() - startSize);
		
//		log.info("Tests finished.");

		
	}
}
