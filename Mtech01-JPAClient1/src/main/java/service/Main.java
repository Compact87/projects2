package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Book;

public class Main {

	public static void main(String[] args) {
		Book book=new Book("Hobbit", 12.9F, "knjiga", "123434534", 250, false);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("chapter04PU");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		em.persist(book);
		tx.commit();
		em.close();
		emf.close();
		
		

	}

}
