package tech01;

import java.util.Random;

import javax.persistence.PostPersist;

public class IsbnGenerator {
    @PostPersist
	public void generateNumber(Book book) {
		String isbn = "13-84356-" + Math.abs(new Random().nextInt());
		book.setIsbn(isbn);;
	}

}
