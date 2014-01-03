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
}
