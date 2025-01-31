package nibm.kd.hdse242.lmscw.controllers;

import nibm.kd.hdse242.lmscw.dto.BookDTO;
import nibm.kd.hdse242.lmscw.entities.Book;
import nibm.kd.hdse242.lmscw.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5501") // Allow frontend to access backend
@RestController
@RequestMapping("/lms/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
      try{
          return bookService.getAllBooks();
      } catch (Exception e) {
          System.out.println(e);
          return null;
      }
    }

    @GetMapping("/{Id}")
    public BookDTO getBook(@PathVariable long Id) {
        try {
            return bookService.getBook(Id);
        }catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @PostMapping
    public Map<String, Object> addBook(@RequestBody BookDTO bookDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            BookDTO addedBook = bookService.addBook(bookDTO);

            response.put("message", "Book added complete");
            response.put("book", addedBook);

        }catch(Exception e) {
            System.out.println(e);
            response.put("message", "book not added");

        }
        return response;
    }

    @PutMapping("/{Id}")
    public Map<String, Object> updateBook(@PathVariable long Id, @RequestBody BookDTO bookDTO) {
        Map<String, Object> response = new HashMap<>();
        try {
            BookDTO updatedBook = bookService.updateBook(Id, bookDTO);
            response.put("message", "Book updated complete");
            response.put("book", updatedBook);
        }catch(Exception e) {
            System.out.println(e);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{Id}")
    public String deleteBook(@PathVariable long Id) {
        try {
            bookService.deleteBook(Id);
            return "Book deleted";
        }catch(Exception e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
}
