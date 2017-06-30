package tech01;


import java.util.List;

import javax.persistence.*;

import tech01.Item;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

	private String company;
	private Float duration;
	 @OneToMany
	 @JoinColumn(name="OWNER_ID", referencedColumnName="ITEM_ID")
		private List<Comment> comments;
	
	/*
	 * CONSTRUCTORS
	 */
	public Movie(String title, Float price, String company, Float duration) {
		super(title, price);
		this.company = company;
		this.duration = duration;
	}

	public Movie() {
		super();
	}
   /*
    * SETTERS/GETTERS
    */
	 public void addComment(Comment comment) {
		   this.comments.add(comment);
	   }
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	
}
