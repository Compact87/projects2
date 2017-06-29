package tech01;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class DataValidationListener {
@PrePersist
@PreUpdate
public void validate(Item item) {
	if(item.getTitle()==null || "".equals(item.getTitle()) )
		throw new IllegalArgumentException(" invalid title");
};

}
