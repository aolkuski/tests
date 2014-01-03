package pl.agh.edu.is.webDB.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class WebDBEntity {

	@Column(name="TIME_CREATED")
	private long timeCreated = 0;
	
	@Column(name="TIME_MODIFIED")
	private long timeModified = 0;
	
	@Column(name="AUTHOR")
	private String author = null;
	
	@Column(name="MODIFIER")
	private String modifier = null;
	
	public long getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(long timeCreated) {
		this.timeCreated = timeCreated;
	}
	public long getTimeModified() {
		return timeModified;
	}
	public void setTimeModified(long timeModified) {
		this.timeModified = timeModified;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}
