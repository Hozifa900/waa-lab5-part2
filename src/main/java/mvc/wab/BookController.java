package mvc.wab;

import java.util.*;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import mvc.CustomErrorType;
import mvc.data.BookRepository;
import mvc.domain.Book;
import mvc.dto.BookDTO;
import mvc.services.BookServiceImpl;
import mvc.services.Books;

@RestController
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        BookDTO book = bookService.getBook(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(
                    new CustomErrorType("Book with isbn= " + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<BookDTO>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        BookDTO book = bookService.getBook(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(
                    new CustomErrorType("Book with isbn = " + isbn + " is not available"), HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@Valid @RequestBody BookDTO book) {
        BookDTO newBook = bookService.addBook(book);
        return new ResponseEntity<BookDTO>(newBook, HttpStatus.OK);
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BookDTO book) {
        BookDTO newBook = bookService.updateBook(isbn, book);
        return new ResponseEntity<BookDTO>(newBook, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> searchBooks(@RequestParam(value = "author", required = false) String author) {
        Books allbooks = bookService.searchBooks(author);
        return new ResponseEntity<Books>(allbooks, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("******************************************");
        System.out.println(ex.getBindingResult().getFieldErrors());
        Map<String, Object> fieldError = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            fieldError.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("data", null);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("fieldError", fieldError);
        return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
    }
}
