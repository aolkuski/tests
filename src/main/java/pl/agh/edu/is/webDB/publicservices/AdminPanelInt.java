	package pl.agh.edu.is.webDB.publicservices;

	import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.Discount;
import pl.agh.edu.is.webDB.entities.Group;
import pl.agh.edu.is.webDB.entities.Right;
import pl.agh.edu.is.webDB.entities.Seller;

	@WebService
	public interface AdminPanelInt {

		@WebMethod
		public List<Discount> getAllDiscounts();

		@WebMethod
		public List<Application> getAllApplications();
		
		@WebMethod
		public void addRandomRight();

		@WebMethod
		public List<Right> getAllRights();

		@WebMethod
		public List<Group> getAllGroups();

		@WebMethod
		public List<Seller> getAllSellers();
		
		@WebMethod
		public Boolean isGroupAllowed(String groupName, String Right);
	}

