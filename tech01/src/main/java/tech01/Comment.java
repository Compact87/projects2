package tech01;


import java.lang.Long;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment  {

	   
	@Id@GeneratedValue
	private Long id;
	

	public Comment() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
   
}
