package mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

import mvc.data.BookRepository;
import mvc.domain.Book;
import mvc.dto.BookAdapter;
import mvc.dto.BookDTO;

@Service
public class BookServiceImpl {

    @Autowired
    BookRepository bookRepository;

    public BookDTO getBook(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);

        return BookAdapter.getBookDTO(book);
    }

    public void deleteBook(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public BookDTO addBook(BookDTO book) {
        Book newBook = bookRepository.save(BookAdapter.getBook(book));
        return BookAdapter.getBookDTO(newBook);
    }

    public BookDTO updateBook(String isbn, BookDTO book) {
        if (!isbn.equals(book.isbn())) {
            return null;
        }
        Book newBook = bookRepository.save(BookAdapter.getBook(book));
        return BookAdapter.getBookDTO(newBook);
    }

    public Books searchBooks(String author) {
        Books books = new Books();
        List<BookDTO> bookDTOs;
        if (author == null) { // get all books
            bookDTOs = bookRepository.findAll().stream().map(BookAdapter::getBookDTO)
                    .collect(Collectors.toList());
            books.setBooks(bookDTOs);
            return books;
        } else { // get books from an certain author
            bookDTOs = bookRepository.findByAuthor(author).stream().map(BookAdapter::getBookDTO)
                    .collect(Collectors.toList());
            books.setBooks(bookDTOs);
            return books;
        }
    }

}
