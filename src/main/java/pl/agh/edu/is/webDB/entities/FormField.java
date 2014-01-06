package pl.agh.edu.is.webDB.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="FORM_FIELD")
public class FormField extends WebDBEntity {

	@Id
	@Column(name="FORM_FIELD_NAME")
	private String formFieldName;
	
	@Column(name="FIELD_TYPE")
	private String fieldType;
	
	@Column(name="DESCRIPTION")
	private String description;

	@ManyToMany(mappedBy="formFields")
	private List<Application> applications = new ArrayList<Application>();
	
	public FormField(){}
	
	public FormField(String pFormFieldName, String pFieldType, String pDescr) {
		formFieldName = pFormFieldName;
		description = pDescr;
		fieldType = pFieldType;
	}

	public String getName() {
		return formFieldName;
	}

	public void setName(String name) {
		this.formFieldName = name;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
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
		sb.append("Form's field with name "+this.getName());
		
		if(this.getDescription() != null){
			sb.append(", described as "+this.getDescription());
		}
		if(this.getFieldType() != null){
			sb.append(" is a type of "+this.getFieldType()+" field");
		}
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
		res += modif * mult + ((this.getFieldType()==null) ? 0 : this.getFieldType().hashCode());
		res += modif * mult + ((this.getName()==null) ? 0 : this.getName().hashCode());
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null && this != null){
			return false;
		}
		if(!this.getName().equals(((FormField)obj).getName())){
			return false;
		}
		return this.hashCode() == ((FormField)obj).hashCode();
	}
	
	/**
	 * Method for obtaining String header for what is present in String returned by {@code getAllDataAsString} method.
	 * @param separator string that should be used to separate values.
	 * @return single String with names of all fields in entity.
	 */
	public String getStringDataHeader(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append("ID/Name"+separator);
		sb.append("Field Type"+separator);
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
		sb.append(this.getName()+separator);
		sb.append(this.getFieldType()+separator);
		sb.append(this.getDescription()+separator);
		
		sb.append(this.getAuthor()+separator);
		sb.append(this.getTimeCreated()+separator);
		sb.append(this.getModifier()+separator);
		sb.append(this.getTimeModified());
		
		return sb.toString();
	}
 
	
	
}
