package tech01;


import java.lang.Long;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Entity implementation class for Entity: Comment
 *
 */
@Entity

public class Comment  {

	   
	@Id@GeneratedValue
	private Long id;
	@Size(min=1, max=100)
	private String content;
	

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
