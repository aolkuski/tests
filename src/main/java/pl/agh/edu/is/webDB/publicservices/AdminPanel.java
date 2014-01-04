package pl.agh.edu.is.webDB.publicservices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import pl.agh.edu.is.webDB.crud.ApplicationsManagement;
import pl.agh.edu.is.webDB.crud.DiscountsManagement;
import pl.agh.edu.is.webDB.crud.GroupsManagement;
import pl.agh.edu.is.webDB.crud.RightsManagement;
import pl.agh.edu.is.webDB.crud.SellersManagement;
import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.Discount;
import pl.agh.edu.is.webDB.entities.Group;
import pl.agh.edu.is.webDB.entities.Right;
import pl.agh.edu.is.webDB.entities.Seller;

@WebService(endpointInterface = "pl.agh.edu.is.webDB.publicservices.AdminPanelInt", serviceName = "AdminPanel")

public class AdminPanel implements AdminPanelInt{

	public List<Discount> getAllDiscounts() {
		ArrayList<Discount> dscts = new ArrayList<Discount>();
		dscts = DiscountsManagement.getAll();
		return dscts;
	}

	public List<Application> getAllApplications() {
		ArrayList<Application> applications = new ArrayList<Application>();
		applications = ApplicationsManagement.getAll();
		return applications;
	}
	
	public void addRandomRight() {
		Right r1 = new Right("nazwa"+Calendar.getInstance().getTimeInMillis(), "opis");
		RightsManagement.add(r1);
	}

	public List<Right> getAllRights() {
		ArrayList<Right> rts = new ArrayList<Right>();
		rts = RightsManagement.getAll();
		return rts;
	}

	public List<Group> getAllGroups() {
		ArrayList<Group> grps = new ArrayList<Group>();
		grps = GroupsManagement.getAll();
		return grps;
	}

	public List<Seller> getAllSellers() {
		ArrayList<Seller> sellers = new ArrayList<Seller>();
		sellers = SellersManagement.getAll();
		return sellers;
	}
	
	public Boolean isGroupAllowed(String groupName, String Right){
		
		return false;
	}
}
