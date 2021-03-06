package tech01;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@EntityListeners(DataValidationListener.class)
@DiscriminatorColumn(name="Discrimin", discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue("I")
@NamedQuery(name="find all Items", query="SELECT i FROM Item i ORDER BY i.title DESC")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Item {
    
	/*
	 * ATTRIBUTES
	 */
	
	
	@Id
	@GeneratedValue
	@Column(name="ITEM_ID")
	protected Long id;
	
	protected String title;
	
	protected Float price;
	
	
	
	/* 
	 * GETTERS/SETTERS
	 */
  
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
    public void setId(Long id) {
    	this.id=id;
    }
	public Long getId() {
		return this.id;
	}
	
	
	/*
	 * CONSTRUCTORS
	 */
	
	



	public Item(String title, Float price) {
		super();
		this.title = title;
		this.price = price;
	}



	public Item() {
		super();
	}
   
}
