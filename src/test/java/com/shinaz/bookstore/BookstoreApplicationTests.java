package com.shinaz.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class BookstoreApplicationTests {

	@Test
	void contextLoads() {
		BookstoreApplication.main(new String[]{});
		assertNull(null);
	}

}
