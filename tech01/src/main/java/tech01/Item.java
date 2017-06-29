package tech01;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Item
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Item implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Item() {
		super();
	}
   
}
