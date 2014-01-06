package pl.agh.edu.is.webDB.privateservices;

import java.util.List;

import javax.jws.WebService;

import pl.agh.edu.is.webDB.crud.ApplicationsManagement;
import pl.agh.edu.is.webDB.crud.DiscountsManagement;
import pl.agh.edu.is.webDB.crud.FormFieldsManagement;
import pl.agh.edu.is.webDB.crud.GroupsManagement;
import pl.agh.edu.is.webDB.crud.RightsManagement;
import pl.agh.edu.is.webDB.crud.SellersManagement;
import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.Discount;
import pl.agh.edu.is.webDB.entities.FormField;
import pl.agh.edu.is.webDB.entities.Group;
import pl.agh.edu.is.webDB.entities.Right;
import pl.agh.edu.is.webDB.entities.Seller;

@WebService(endpointInterface = "pl.agh.edu.is.webDB.privateservices.CrudServiceInterface", serviceName = "CrudService")
public class CrudService implements CrudServiceInterface {

	@Override
	public List<Discount> getAllDiscounts() {
		return DiscountsManagement.getAll();
	}

	@Override
	public List<Application> getAllApplications() {
		return ApplicationsManagement.getAll();
	}

	@Override
	public List<Right> getAllRights() {
		return RightsManagement.getAll();
	}

	@Override
	public List<Group> getAllGroups() {
		return GroupsManagement.getAll();
	}

	@Override
	public List<Seller> getAllSellers() {
		return SellersManagement.getAll();
	}

	@Override
	public List<FormField> getAllFormFields() {
		return FormFieldsManagement.getAll();
	}

	@Override
	public void addGroup(Group grp) {
		GroupsManagement.add(grp);

	}

	@Override
	public void addRight(Right right) {
		RightsManagement.add(right);
	}

	@Override
	public void addApplicationForm(Application app) {
		ApplicationsManagement.add(app);
	}

	@Override
	public void addDiscount(Discount dsc) {
		DiscountsManagement.add(dsc);
	}

	@Override
	public void addFormField(FormField ff) {
		FormFieldsManagement.add(ff);
	}

	
	@Override
	public void addSeller(Seller sr) {
		SellersManagement.add(sr);

	}

	@Override
	public void deleteGroup(Group grp) {
		GroupsManagement.delete(grp.getGroupName());
	}

	@Override
	public void deleteRight(Right right) {
		RightsManagement.delete(right.getRightName());
	}

	@Override
	public void deleteApplicationForm(Application app) {
		ApplicationsManagement.delete(String.valueOf(app.getId()));
	}

	@Override
	public void deleteDiscount(Discount dsc) {
		DiscountsManagement.delete(dsc.getDiscountName());
	}

	@Override
	public void deleteFormField(FormField ff) {
		FormFieldsManagement.delete(ff.getName());
	}

	@Override
	public void deleteSeller(Seller sr) {
		SellersManagement.delete(String.valueOf(sr.getEntityId()));
	}

	@Override
	public void modifyGroup(Group grp) {
		GroupsManagement.update(grp);
	}

	@Override
	public void modifyRight(Right right) {
		RightsManagement.update(right);
	}

	@Override
	public void modifyApplicationForm(Application app) {
		ApplicationsManagement.update(app);
	}

	@Override
	public void modifyDiscount(Discount dsc) {
		DiscountsManagement.update(dsc);
	}

	@Override
	public void modifyFormField(FormField ff) {
		FormFieldsManagement.update(ff);
	}

	@Override
	public void modifySeller(Seller sr) {
		SellersManagement.update(sr);
	}

	@Override
	public Boolean isGroupAllowed(String groupName, String rightName) {
		Group grp = GroupsManagement.findById(groupName);
		List<Right> rights = grp.getRights();
		for(Right r:rights){
			if(r.getRightName().equals(rightName)){
				return true;
			}
		}
		return false;
	}

}
