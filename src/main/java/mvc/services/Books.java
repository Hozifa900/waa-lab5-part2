package mvc.services;

import java.util.ArrayList;
import java.util.Collection;
import mvc.dto.BookDTO;

public class Books {
    private Collection<BookDTO> books = new ArrayList<>();

    public Collection<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(Collection<BookDTO> books) {
        this.books = books;
    }
}
