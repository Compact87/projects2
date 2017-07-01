package tech01;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import tech01.Item;

/**
 * Entity implementation class for Entity: Book
 *
 */
@Entity
@DiscriminatorValue("B")
@EntityListeners(IsbnGenerator.class)
public class Book extends Item  {
@NotNull
@Transient
 private String isbn;
 private String publisher;
 private int nbOfPage;
 private boolean illustrations;
 @OneToMany
 @JoinColumn(name="OWNER_ID", referencedColumnName="ITEM_ID")
	private List<Comment> comments;
	/*
	 * GETTERS/SETTERS
	 */
   public void addComment(Comment comment) {
	   this.comments.add(comment);
   }
 
 
 
	public String getIsbn() {
	return isbn;
}




public void setIsbn(String isbn) {
	this.isbn = isbn;
}




public String getPublisher() {
	return publisher;
}




public void setPublisher(String publisher) {
	this.publisher = publisher;
}




public int getNbOfPage() {
	return nbOfPage;
}




public void setNbOfPage(int nbOfPage) {
	this.nbOfPage = nbOfPage;
}




public boolean isIllustrations() {
	return illustrations;
}




public void setIllustrations(boolean illustrations) {
	this.illustrations = illustrations;
}

 /* 
  * CONSTRUCTORS
  */
   

	public Book() {
		super();
	}




public Book(String title, Float price, String publisher,
		int nbOfPage, boolean illustrations) {
	super(title, price);
	
	this.publisher = publisher;
	this.nbOfPage = nbOfPage;
	this.illustrations = illustrations;
}
   
}
