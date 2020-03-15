package spring_boot.formatters;/**
 * @author Sergey Zhernovoy
 * create on 22.11.2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import spring_boot.entity.Book;
import spring_boot.repositories.BookRepository;

import java.text.ParseException;
import java.util.Locale;


public class BookFormater implements Formatter<Book> {
    private static final Logger logger = LoggerFactory.getLogger(BookFormater.class);
    private BookRepository bookRepository;

    public BookFormater(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book parse(String bookIdentifier, Locale locale) throws ParseException {
        Book book = bookRepository.findBookByIsbn(bookIdentifier);
        return book != null ? book : bookRepository.findById(Long.valueOf(bookIdentifier)).get();
    }

    @Override
    public String print(Book book, Locale locale) {
        return book.getIsbn();
    }
}

    