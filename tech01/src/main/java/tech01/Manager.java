package tech01;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Manager {

	@Inject
	private ItemEJB itemEJB;
	private Item item=new Item();
	private Book book=new Book();
	private Movie movie=new Movie();
	
	
	public String createItem(Item item) {
		itemEJB.createItem(item);
		return newItem.xhtml; 
	}

}
