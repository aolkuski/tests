package pl.agh.edu.is.webDB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SELLER")
public class Seller extends WebDBEntity{

	@Id
	@Column(name="ENTITY_ID")
	private Integer entityId;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	private Seller(){}
	
	public Seller(Integer pId, String pAddress, String pType, String pDescr) {
		entityId = pId;
		address = pAddress;
		type = pType;
		description = pDescr;
	}
	
	public Integer getEntityId() {
		return entityId;
	}
	public void setEntityId(Integer id) {
		this.entityId = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
		sb.append("Seller with id "+this.getEntityId());
		
		if(this.getAddress() != null){
			sb.append(" is located in place "+this.getAddress());
		}
		
		if(this.getDescription() != null){
			sb.append(", described as "+this.getDescription());
		}
		if(this.getType() != null){
			sb.append(" and is a type of "+this.getType());
		}
		sb.append(". Autor: ");
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
		res += modif * mult + ((this.getAddress()==null) ? 0 : this.getAddress().hashCode());
		res += modif * mult + ((this.getAuthor()==null) ? 0 : this.getAuthor().hashCode());
		res += modif * mult + ((this.getDescription() == null) ? 0 : this.getDescription().hashCode());
		res += modif * mult + ((this.getTimeCreated()==0) ? 0 : this.getTimeCreated());
		res += modif * mult + ((this.getType()==null) ? 0 : this.getType().hashCode());
		res += modif * mult + this.getEntityId().hashCode();
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null && this != null){
			return false;
		}
		if(this.entityId != ((Seller)obj).getEntityId()){
			return false;
		}
		return this.hashCode() == ((Seller)obj).hashCode();
	}
 
	
	/**
	 * Method for obtaining String header for what is present in String returned by {@code getAllDataAsString} method.
	 * @param separator string that should be used to separate values.
	 * @return single String with names of all fields in entity.
	 */
	public String getStringDataHeader(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append("ID/Name"+separator);
		sb.append("Address"+separator);
		sb.append("Type"+separator);
		sb.append("Description"+separator);
	
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
	public String getAllDataAsString(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getEntityId()+separator);
		sb.append(this.getAddress()+separator);
		sb.append(this.getType()+separator);
		sb.append(this.getDescription()+separator);
		
		sb.append(this.getAuthor()+separator);
		sb.append(this.getTimeCreated()+separator);
		sb.append(this.getModifier()+separator);
		sb.append(this.getTimeModified());
		
		return sb.toString();
	}
	
}
