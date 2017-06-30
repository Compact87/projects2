package tech01;


import javax.persistence.*;
import tech01.Item;

/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

	
	

	public Movie() {
		super();
	}
   
}
