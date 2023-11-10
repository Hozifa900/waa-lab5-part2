package mvc.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import mvc.domain.Book;
import mvc.services.Books;

@Repository

public interface BookRepository extends MongoRepository<Book, String> {
    public Book findByIsbn(String isbn);

    public void deleteByIsbn(String isbn);

    public Book save(Book book);

    public List<Book> findByAuthor(String author);
}
