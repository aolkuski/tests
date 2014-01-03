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
	
	public Seller(){}
	
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
 
	
}
