package model;

import javax.persistence.*;
import javax.validation.constraints.*;



@Entity
@NamedQueries({
    @NamedQuery(name="findAllBooks",
                query="SELECT c FROM Book c"),
    @NamedQuery(name="findBook",
                query="SELECT c FROM Book c WHERE c.title = 'Hobbit'"),
}) 
public class Book {
  @Id@GeneratedValue
   private Long id; 
  @NotNull
  private String title;
  private Float price;
  @Size(min = 3, max = 2000)
  private String description;
  private String isbn;
  private Integer nbOfPage;
  private Boolean illustrations;
  
  public Book(){
	  
  }
  
public Book(String title, Float price, String description, String isbn,
		Integer nbOfPage, Boolean illustrations) {
	super();
	this.title = title;
	this.price = price;
	this.description = description;
	this.isbn = isbn;
	this.nbOfPage = nbOfPage;
	this.illustrations = illustrations;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public Float getPrice() {
	return price;
}

public void setPrice(Float price) {
	this.price = price;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public Integer getNbOfPage() {
	return nbOfPage;
}

public void setNbOfPage(Integer nbOfPage) {
	this.nbOfPage = nbOfPage;
}

public Boolean getIllustrations() {
	return illustrations;
}

public void setIllustrations(Boolean illustrations) {
	this.illustrations = illustrations;
}

@Override
public String toString() {
	return "Book [id=" + id + ", title=" + title + ", price=" + price
			+ ", description=" + description + ", isbn=" + isbn + ", nbOfPage="
			+ nbOfPage + ", illustrations=" + illustrations + "]";
}
  
  
}
