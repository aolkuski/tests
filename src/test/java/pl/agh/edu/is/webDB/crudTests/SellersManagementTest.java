package pl.agh.edu.is.webDB.crudTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

//import org.apache.log4j.Logger;
import org.junit.Test;

import pl.agh.edu.is.webDB.crud.SellersManagement;
import pl.agh.edu.is.webDB.entities.Seller;

public class SellersManagementTest {
//	Logger log = Logger.getLogger(this.getClass().getName());
	
	@Test
	public void test(){
		Seller sr1 = new Seller(1, "ul. Czarnowiejska 1/3 4", "punkt informacyjny", "wulkanizator");
		Seller sr2 = new Seller(2, "ul. Czarnowiejska 2/3  4", "ubezpieczyciel", "PZU");
		Seller sr3 = new Seller(3, "ul. Czarnowiejska 3/3   4", "apteka", "dbam o chorobęr");
		Seller sr4 = new Seller(4, "ul. Czarnowiejska 4/3    4", "automat", "budka na skryżowaniu");
		Seller sr5 = new Seller(5, "ul. Czarnowiejska 5/3     4", "jadalnia", "u rudego");
		
		
		SellersManagement.add(sr1);
		SellersManagement.add(sr2);
		SellersManagement.add(sr3);
		SellersManagement.add(sr4);
		SellersManagement.add(sr5);
		
		ArrayList<Seller> sellers = SellersManagement.getAll();
		for(Seller s:sellers){
			System.out.println(s);
		}
		
		sr2.setDescription("changed PZU");
		SellersManagement.update(sr2);
		
		System.out.println(SellersManagement.findById("2"));
		System.out.println(sr2);
		
		assertTrue(sr2.equals(SellersManagement.findById("2")));
		assertEquals(5, sellers.size());
		assertNotSame(sr2, SellersManagement.findById("3"));
//		log.info("Tests finished.");

		
	}
}
