package tech01;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	/* 
	 * NEW ITEM
	 */
	public String createItem() {
		itemEJB.createItem(this.item);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item created",
	            "The item" + item.getTitle() + " has been created with id=" + item.getId()));
		return "newItem.xhtml"; 
	}
	public void doFindItemById(Long id) {  
		item =itemEJB.findItemById(id);
		/* 
		 *mozda mora return item
		 */
	}
	
	/*
	 * NEW BOOK
	 */
	public String doCreateBook() {
	    itemEJB.createBook(book);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book created",
	            "The book" + book.getTitle() + " has been created with id=" + book.getId()));
	    return "newItem.xhtml";
	  }

	  public void doFindBookById() {
	    book =itemEJB.findBookById(book.getId());
	  }
	/* 
	 * NEW MOVIE
	 */
	
	  public String doCreateMovie() {
		    itemEJB.createMovie(movie);
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Movie created",
		            "The movie" + movie.getTitle() + " has been created with id=" + movie.getId()));
		    return "newItem.xhtml";
		  }

		  public void doFindMovieById() {
		   movie = itemEJB.findMovieById(movie.getId());
		  }
	  
	/* 
	 * Getter & Setters
	 */
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
