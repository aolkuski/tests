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
@Table(name = "APPLICATION")
public class Application extends WebDBEntity {

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "APPLICANT")
	private String applicant;

	@Column(name = "SUPERVISOR")
	private String supervisor;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "APPLICATION_FIELDS", joinColumns = { @JoinColumn(name = "ID") }, inverseJoinColumns = { @JoinColumn(name = "FORM_FIELD_NAME") })
	private List<FormField> formFields;

	private Application() {
	};

	public Application(Integer pId, String pType, String pApplicant, String pSupervisor, String pDescr, ArrayList<FormField> fields) {
		id = pId;
		type = pType;
		applicant = pApplicant;
		supervisor = pSupervisor;
		description = pDescr;
		formFields = fields;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FormField> getFormFields() {
		return formFields;
	}

	public void setFormFields(List<FormField> fields) {
		this.formFields = fields;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Application no. ");
		sb.append(this.getId());
		if (this.description != null) {
			sb.append(" described as \"");
			sb.append(this.getDescription() + "\" ");
		}
		if ((this.applicant != null) && (this.applicant != "")) {
			sb.append(" applicable by \"");
			sb.append(this.getApplicant() + "\" ");
		}
		if ((this.formFields != null) && this.formFields.size() > 0) {
			sb.append(" with fields: ");
			for (FormField ff : this.formFields) {
				sb.append(ff.getName() + ", ");
			}
			sb.append(this.getApplicant() + "\" ");
		}
		if ((this.supervisor != null) && (this.supervisor != "")) {
			sb.append(" can be supervised by \"");
			sb.append(this.getSupervisor() + "\" ");
		}
		sb.append(".");

		return sb.toString();
	}

	public boolean equals(Application app) {
		if (!this.getApplicant().equals(app.getApplicant()))
			return false;
		if (!this.getAuthor().equals(app.getAuthor()))
			return false;
		if (!this.getDescription().equals(app.getDescription()))
			return false;
		if (!this.getApplicant().equals(app.getApplicant()))
			return false;
		if (!this.getId().equals(app.getId()))
			return false;
		if (!this.getSupervisor().equals(app.getSupervisor()))
			return false;
		if (!this.getType().equals(app.getType()))
			return false;

		boolean flag = false;
		if (this.formFields != null) {
			if (this.formFields.size() != app.getFormFields().size()) {
				return false;
			}
			for (int i = 0; i < this.formFields.size(); i++) {
				flag = false;
				for (int j = 0; j < app.getFormFields().size(); j++) {
					if (this.formFields.get(i).getName().equals(app.getFormFields().get(j).getName())) {
						flag = true;
						break;
					}
				}
				if (!flag)
					return false;
			}
		}

		return true;

	}

}
