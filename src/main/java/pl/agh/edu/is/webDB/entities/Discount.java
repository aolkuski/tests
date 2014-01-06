package pl.agh.edu.is.webDB.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DISCOUNT")
public class Discount extends WebDBEntity {

	public Discount(){}
	
	public Discount(String pName, Double pVal, String pInsuranceType,
			String pUnit, Integer pBottomLimit, Integer pTopLimit,
			String pLimitedFieldValue, Boolean pActive) {
		discountName = pName;
		value = pVal;
		insuranceType = pInsuranceType;
		unit = pUnit;
		bottomLimit = pBottomLimit;
		topLimit = pTopLimit;
		limitedFieldName = pLimitedFieldValue;
		active= pActive;
	}

	@Id
	@Column(name="DISCOUNT_NAME")
	private String discountName;
	
	@Column(name="VALUE")
	private Double value;
	
	@Column(name="INSURANCE_TYPE")
	private String insuranceType;
	
	@Column(name="UNIT")
	private String unit;
	
	@Column(name="BOTTMO_LIMIT")
	private Integer bottomLimit;
	
	@Column(name="TOP_LIMIT")
	private Integer topLimit;
	
	@Column(name="LIMITED_FIELD_NAME")
	private String limitedFieldName;
	
	@Column(name="ACTIVE")
	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String pDiscountName) {
		this.discountName = pDiscountName;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getBottomLimit() {
		return bottomLimit;
	}

	public void setBottomLimit(Integer bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	public Integer getTopLimit() {
		return topLimit;
	}

	public void setTopLimit(Integer topLimit) {
		this.topLimit = topLimit;
	}

	public String getLimitedFieldName() {
		return limitedFieldName;
	}

	public void setLimitedFieldName(String limitedFieldName) {
		this.limitedFieldName = limitedFieldName;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Discount with name \""+this.getDiscountName()+"\" has properties: ");
		if(this.getBottomLimit() != null){
			sb.append(" bottom limit = "+this.getBottomLimit()+", ");
		}
		if(this.getInsuranceType() != null){
			sb.append("applicable on: "+this.getInsuranceType()+", ");
		}
		if(this.getLimitedFieldName() != null){
			sb.append("applicable to field: "+this.getLimitedFieldName()+", ");
		}
		if(this.getTopLimit() != null){
			sb.append("top limit = "+this.getTopLimit()+", ");
		}
		if(this.getUnit() != null){
			sb.append("unit: "+this.getUnit()+", ");
		}
		if(this.getValue() != null){
			sb.append("equals "+this.getValue());
		}
		sb.append( (this.getActive() ? " and is active" : " and is inactive"));
		sb.append(".");
		sb.append(" Discount created @");
		sb.append(this.getTimeCreated());
		
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null && this != null){
			return false;
		}
		if(!this.getDiscountName().equals(((Discount)obj).getDiscountName())){
			return false;
		}
		return this.hashCode() == ((Discount)obj).hashCode();
	}
	
	@Override
	public int hashCode(){
		int modif = 4;
		int mult = 2;
		int res = 0;
		res += modif * mult + ((this.getAuthor()==null) ? 0 : this.getAuthor().hashCode());
		res += modif * mult + ((this.getDiscountName() == null) ? 0 : this.getDiscountName().hashCode());
		res += modif * mult + ((this.getInsuranceType() == null) ? 0 : this.getInsuranceType().hashCode());
		res += modif * mult + ((this.getLimitedFieldName() == null) ? 0 : this.getLimitedFieldName().hashCode());
		res += modif * mult + this.getTimeCreated();
		res += modif * mult + ((this.getUnit() == null) ? 0 : this.getUnit().hashCode());
		res += modif * mult + ((this.getValue() == null) ? 0 : this.getValue().hashCode());
		
		return res;
	}
	
	/**
	 * Method for obtaining String header for what is present in String returned by {@code getAllDataAsString} method.
	 * @param separator string that should be used to separate values.
	 * @return single String with names of all fields in entity.
	 */
	public String getStringDataHeader(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append("ID/Name"+separator);
		sb.append("Insurance Type"+separator);
		sb.append("Unit"+separator);
		sb.append("Bottom Limit"+separator);
		sb.append("Top Limit"+separator);
		sb.append("Limited Field Name"+separator);
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
	public String getAllDataAsString(String separator){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getDiscountName()+separator);
		sb.append(this.getInsuranceType()+separator);
		sb.append(this.getUnit()+separator);
		sb.append(this.getBottomLimit()+separator);
		sb.append(this.getTopLimit()+separator);
		sb.append(this.getLimitedFieldName()+separator);
		sb.append(this.getActive()+separator);
		
		sb.append(this.getAuthor()+separator);
		sb.append(this.getTimeCreated()+separator);
		sb.append(this.getModifier()+separator);
		sb.append(this.getTimeModified());
		
		return sb.toString();
	}

}
