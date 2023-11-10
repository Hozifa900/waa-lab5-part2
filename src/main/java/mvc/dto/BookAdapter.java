package mvc.dto;

import mvc.domain.Book;

public class BookAdapter {
    public static Book getBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            return new Book();
        }
        return new Book(bookDTO.isbn(), bookDTO.title(), bookDTO.price(), bookDTO.author());
    }

    public static BookDTO getBookDTO(Book book) {
        if (book == null) {
            return new BookDTO(null, null, 0.0, null);
        }
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getPrice(), book.getAuthor());
    }

}
