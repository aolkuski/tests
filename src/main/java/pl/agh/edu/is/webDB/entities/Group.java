package pl.agh.edu.is.webDB.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="APP_GROUP")
public class Group extends WebDBEntity{

	@Id
	@Column(name="GROUP_NAME")
	private String groupName;
	
	@Column(name="ACTIVE")
	private Boolean active;
	
	@Column(name="DESSCRIPTION")
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name="GROUP_RIGHT",
			joinColumns={@JoinColumn(name="NAME")},
			inverseJoinColumns={@JoinColumn(name="RIGHT_NAME")})
	private List<Right> rights = new ArrayList<Right>();
	
	public Group(){}
	
	public Group(String pGroupName, String pDescr, Boolean pActive) {
		groupName = pGroupName;
		description = pDescr;
		active = pActive;
	}
	
	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String name) {
		this.groupName = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Group called "+this.getGroupName());
		
		if(this.getDescription() != null){
			sb.append(" is described as "+this.getDescription());
		}
		sb.append( (this.getActive() ? " and is active" : " and is inactive"));
		
		sb.append(". Author: ");
		sb.append(this.getAuthor());
		sb.append(", created at: ");
		sb.append(this.getTimeCreated());
		sb.append(".");
		
		return sb.toString();
	}
	
	@Override
	public int hashCode(){
		int modif = 4;
		int mult = 2;
		int res = 0;
		res += modif * mult + ((this.getAuthor()==null) ? 0 : this.getAuthor().hashCode());
		res += modif * mult + ((this.getDescription() == null) ? 0 : this.getDescription().hashCode());
		res += modif * mult + ((this.getTimeCreated()==0) ? 0 : this.getTimeCreated());
		res += modif * mult + ((this.getGroupName()==null) ? 0 : this.getGroupName().hashCode());
		if(this.getRights().size() > 0){
			for(Right r:this.getRights()){
				res += modif * mult + r.hashCode();
			}
		}
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null && this != null){
			return false;
		}
		if(!this.getGroupName().equals(((Group)obj).getGroupName())){
			return false;
		}
		return this.hashCode() == ((Group)obj).hashCode();
	}
	
	/**
	 * Method for obtaining String header for what is present in String returned by {@code getAllDataAsString} method.
	 * @param separator string that should be used to separate values.
	 * @return single String with names of all fields in entity.
	 */
	public String getStringDataHeader(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append("ID/Name"+separator);
		sb.append("Rights"+separator);
		sb.append("Description"+separator);
		sb.append("Is Active"+separator);
	
		sb.append("Author"+separator);
		sb.append("Time Created"+separator);
		sb.append("Modifier"+separator);
		sb.append("Time Modified");
		
		
		return sb.toString();
	}
	
	/**
	 * Returns all data from entity, separated by 'separator'. If any nested lists are present, then 'secondLevelSeparator' is used to separate those values (like fields in application)
	 * @param separator separator for data (id, type, description etc)
	 * @param secondLevelSeparator separator for nested lists (e.g. fields in application)
	 * @return single String made of all data 
	 */
	public String getAllDataAsString(String separator, String secondLevelSeparator){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getGroupName()+separator);
		for(Right r:this.getRights()){
			sb.append(r.getRightName()+secondLevelSeparator);
		}
		sb.append(separator);
		sb.append(this.getDescription()+separator);
		sb.append(this.getActive()+separator);
		
		sb.append(this.getAuthor()+separator);
		sb.append(this.getTimeCreated()+separator);
		sb.append(this.getModifier()+separator);
		sb.append(this.getTimeModified());
		
		return sb.toString();
	}
}
