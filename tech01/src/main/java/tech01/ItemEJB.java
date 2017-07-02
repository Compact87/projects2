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
	 * Item
	 */
	public Item createItem(Item item) {
		em.persist(item);
		return item;
	}
  public List<Item> findAllItems(){
	 return em.createNamedQuery("find all Items",Item.class).getResultList();
	  
  }
  public Item findItemById(Long id) {
	  return em.find(Item.class, id);
  }
  /*
   * Book
   */
  public Book createBook(Book book) {
		em.persist(book);
		return book;
	}
public List<Book> findAllBooks(){
	 return em.createNamedQuery("find all Items",Book.class).getResultList();
	 }
public Book findBookById(Long id) {
	return em.find(Book.class, id);
}
 /* 
  * Movie
  */
public Movie createMovie(Movie movie) {
	em.persist(movie);
	return movie;
}
public List<Movie> findAllMovies(){
 return em.createNamedQuery("find all Items",Movie.class).getResultList();
  }
public Movie findMovieById(Long id) {
	return em.find(Movie.class, id);
}
}
