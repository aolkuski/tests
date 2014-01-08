package pl.agh.edu.is.webDB.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "APP_RIGHT")
public class Right extends WebDBEntity {

	@Id
	@Column(name = "RIGHT_NAME")
	private String rightName;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "rights")
	private List<Group> groups = new ArrayList<Group>();

	private Right() {
	}

	public Right(String pRightName, String pDesc) {
		rightName = pRightName;
		description = pDesc;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String pRightName) {
		this.rightName = pRightName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Right called " + this.getRightName());

		if (this.getDescription() != null) {
			sb.append(" is described as " + this.getDescription());
		}

		sb.append(". Author: ");
		sb.append(this.getAuthor());
		sb.append(", created at: ");
		sb.append(this.getTimeCreated());
		sb.append(".");

		return sb.toString();
	}

	@Override
	public int hashCode() {
		int modif = 4;
		int mult = 2;
		int res = 0;
		res += modif
				* mult
				+ ((this.getAuthor() == null) ? 0 : this.getAuthor().hashCode());
		res += modif
				* mult
				+ ((this.getDescription() == null) ? 0 : this.getDescription()
						.hashCode());
		res += modif * mult
				+ ((this.getTimeCreated() == 0) ? 0 : this.getTimeCreated());
		res += modif
				* mult
				+ ((this.getRightName() == null) ? 0 : this.getRightName()
						.hashCode());

		return res;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null && this != null) {
			return false;
		}
		if (!this.getRightName().equals(((Right) obj).getRightName())) {
			return false;
		}
		return this.hashCode() == ((Right) obj).hashCode();
	}

	/**
	 * Returns all data from entity, separated by 'separator'. If any nested
	 * lists are present, then 'secondLevelSeparator' is used to separate those
	 * values (like fields in application)
	 * 
	 * @param separator
	 *            separator for data (id, type, description etc)
	 * @param secondLevelSeparator
	 *            separator for nested lists (e.g. fields in application)
	 * @return single String made of all data
	 */
	@Transient
	public String getAllDataAsString(String separator) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRightName() + separator);
		sb.append(this.getDescription() + separator);

		sb.append(this.getAuthor() + separator);
		sb.append(this.getTimeCreated() + separator);
		sb.append(this.getModifier() + separator);
		sb.append(this.getTimeModified());

		return sb.toString();
	}
}
