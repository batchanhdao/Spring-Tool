package QLS;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {
	@Id
	private String bookcode;
	private String title;
	private String author;
	private String category;
	private int booked;
	
	public Book() {
		
	}
	
	public Book(String bookcode, String title, String author, String category, int booked) {
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.category = category;
		this.booked = booked;
	}
	
}
