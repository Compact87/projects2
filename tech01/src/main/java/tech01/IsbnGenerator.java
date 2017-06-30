package tech01;

import java.util.Random;

import javax.persistence.PostPersist;
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {
    @PostPersist
	public String generateNumber() {
		String isbn = "13-84356-" + Math.abs(new Random().nextInt());
		return isbn;
	}

}
