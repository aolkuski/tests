package pl.agh.edu.is.webDB.privateservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pl.agh.edu.is.webDB.entities.Application;
import pl.agh.edu.is.webDB.entities.Discount;
import pl.agh.edu.is.webDB.entities.FormField;
import pl.agh.edu.is.webDB.entities.Group;
import pl.agh.edu.is.webDB.entities.Right;
import pl.agh.edu.is.webDB.entities.Seller;

@WebService
public interface CrudServiceInterface {

	@WebMethod
	public List<Discount> getAllDiscounts();

	@WebMethod
	public List<Application> getAllApplications();

	@WebMethod
	public List<Right> getAllRights();

	@WebMethod
	public List<Group> getAllGroups();

	@WebMethod
	public List<Seller> getAllSellers();

	@WebMethod
	public List<FormField> getAllFormFields();
	
	@WebMethod
	public Boolean isGroupAllowed(String groupName, String Right);
	

	
	@WebMethod
	public void addGroup(Group grp);

	@WebMethod
	public void addRight(Right right);

	@WebMethod
	public void addApplicationForm(Application app);

	@WebMethod
	public void addDiscount(Discount dsc);

	@WebMethod
	public void addFormField(FormField ff);

	@WebMethod
	public void addSeller(Seller sr);
	

	
	@WebMethod
	public void deleteGroup(Group grp);

	@WebMethod
	public void deleteRight(Right right);

	@WebMethod
	public void deleteApplicationForm(Application app);

	@WebMethod
	public void deleteDiscount(Discount dsc);

	@WebMethod
	public void deleteFormField(FormField ff);

	@WebMethod
	public void deleteSeller(Seller sr);
	

	
	@WebMethod
	public void modifyGroup(Group grp);

	@WebMethod
	public void modifyRight(Right right);

	@WebMethod
	public void modifyApplicationForm(Application app);

	@WebMethod
	public void modifyDiscount(Discount dsc);

	@WebMethod
	public void modifyFormField(FormField ff);

	@WebMethod
	public void modifySeller(Seller sr);

	
	
	@WebMethod
	public String getRightsStringDataHeader(String separator);

	@WebMethod
	public String getSellersStringDataHeader(String separator);
	
	@WebMethod
	public String getGroupsStringDataHeader(String separator);
	
	@WebMethod
	public String getFormFieldsStringDataHeader(String separator);
	
	@WebMethod
	public String getDiscountsStringDataHeader(String separator);

	@WebMethod
	public String getApplicationsStringDataHeader(String separator);
	
	
	

	@WebMethod
	public List<String> getAllDiscountsAsSeparatedStringsList(String separator);
	
	@WebMethod
	public List<String> getAllFormFieldsAsSeparatedStringsList(String separator);
	
	@WebMethod
	public List<String> getAllRightsAsSeparatedStringsList(String separator);
	
	@WebMethod
	public List<String> getAllSellersAsSeparatedStringsList(String separator);
	
	@WebMethod
	public List<String> getAllApplicationsAsSeparatedStringsList(String separator, String secondLevelSeparator);

	@WebMethod
	public List<String> getAllGroupsAsSeparatedStringsList(String separator, String secondLevelSeparator);
	
	
	
	
}
