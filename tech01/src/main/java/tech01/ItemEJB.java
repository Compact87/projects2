/**
 * 
 */
package tech01;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
@Named
@Stateless
/**
 * @author Uros
 *
 */
public class ItemEJB {
	/**
	 * Attributes
	 */
	@Inject
	private EntityManager em;

	/**
	 * 
	 */
	public Item createItem(Item item) {
		em.persist(item);
		return item;
	}
  public List<Item> findAllItems(){
	 return em.createNamedQuery("find all Items",Item.class).getResultList();
	  
  }
}
