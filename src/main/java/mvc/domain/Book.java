package mvc.domain;

import org.springframework.data.annotation.Id;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Book {
	@NonNull
	@NotBlank
	@Size(min = 2, max = 20)
	@Id
	private String isbn;
	private String title;
	private double price;
	@NotBlank
	@NotNull
	private String author;

	public Book() {

	}

	public Book(String isbn, String title, double price, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
