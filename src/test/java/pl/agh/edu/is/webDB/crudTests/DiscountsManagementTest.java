package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.DiscountsManagement;
import pl.agh.edu.is.webDB.entities.Discount;

public class DiscountsManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		Discount obj1 = new Discount("znizka specjalna", 10.1, "AC", "percent", 10, 20, "wiek", true);
		Discount obj2 = new Discount("znizka swiateczna", 10.2, "OC", "point", 10, 20, "wiek", true);
		Discount obj3 = new Discount("zwyzka noworoczna", 10.3, "NW", "percent", 10, 20, "wiek", false);
		Discount obj4 = new Discount("promocja dla studentow", 10.4, "NW", "percent", 10, 20, "wiek", true);
		Discount obj5 = new Discount("znizka niespecjalna", 10.5, "AC", "percent", 10, 20, "wiek", true);
		
		
		DiscountsManagement.add(obj1);
		DiscountsManagement.add(obj2);
		DiscountsManagement.add(obj3);
		DiscountsManagement.add(obj4);
		DiscountsManagement.add(obj5);
		
		ArrayList<Discount> discounts = DiscountsManagement.getAll();
		for(Discount obj:discounts){
			System.out.println(obj);
		}
		
		obj2.setInsuranceType("nowy typ");
		obj2.setActive(false);
		DiscountsManagement.update(obj2);
		
		System.out.println(DiscountsManagement.findById("znizka swiateczna"));
		System.out.println(obj2);
		
		assertTrue(obj2.equals(DiscountsManagement.findById("znizka swiateczna")));
		assertEquals(5, discounts.size());
		assertNotSame(obj2, DiscountsManagement.findById("3"));
//		log.info("Tests finished.");

		
	}
}
