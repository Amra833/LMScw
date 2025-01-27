package nibm.kd.hdse242.lmscw.services;

import nibm.kd.hdse242.lmscw.dto.BookDTO;
import nibm.kd.hdse242.lmscw.entities.Book;
import nibm.kd.hdse242.lmscw.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Create Books
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);
        return bookDTO;
    }

    //Retrieve Books
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setId(book.getId());
            bookDTO.setAuthor(book.getAuthor());
            bookDTO.setTitle(book.getTitle());
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    //Retrieve a Book
    public BookDTO getBook(long Id) {
        Book book = bookRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"BooK Not Found"));
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        return bookDTO;
    }

    //Update a Book
    public BookDTO updateBook(long Id, BookDTO bookDTO) {
        Book book = bookRepository.findById(Id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"BooK Not Found"));
        //book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        bookRepository.save(book);
        return bookDTO;
    }

    //Delete a Book
    public BookDTO deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found"));
        bookRepository.deleteById(id);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());

        return bookDTO;
    }

}
