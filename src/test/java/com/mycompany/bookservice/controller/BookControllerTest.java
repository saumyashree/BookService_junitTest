package com.mycompany.bookservice.controller;
import com.mycompany.bookservice.service.BookService;
import com.mycompany.bookservice.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.assertj.core.api.Assert;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.mockito.ArgumentMatchers;
import org.springframework.web.bind.annotation.PathVariable;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    //Mockito will give memory to PropertyService and it will inject this dummy/proxy PropertyService object inside the proxy/dummy object of PropertyController
    @Mock
    private BookService bookService;


    @Test
    @DisplayName("Test Success Scenario for get all book")
    void testGetBook() {
        List<BookDTO> bookList = new ArrayList<>();
        BookDTO dto = new BookDTO();
        dto.setBookId(1L);

        dto.setName("Dummy getallBook");
        bookList.add(dto);

        //Do not make the actual call for propertyService.getAllProperties() inside controller rather return dummy List<PropertyDTO> in the controller
        when(bookService.getAllBook()).thenReturn(bookList);
        ResponseEntity<List<BookDTO>> responseEntity = bookController.getAllBook();
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

    }

    @Test
    @DisplayName("Test Success Scenario for updating only price of the Book")
    void testUpdateBookPrice() {

        BookDTO dto = new BookDTO();
        dto.setPricePerQty(67.78);

        when(bookService.updateBookPrice(Mockito.any(), Mockito.anyLong())).thenReturn(dto);
        ResponseEntity<BookDTO> responseEntity = bookController.updateBookPrice(dto, 1L);

        assertEquals(67.78, responseEntity.getBody().getPricePerQty());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

    }

    @Test
    @DisplayName("Test Success Scenario addBook")
    void testAddBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Dummy add");
        BookDTO addBook = new BookDTO();
        addBook.setBookId(1L);
        Mockito.when(bookService.addBook(bookDTO)).thenReturn(addBook);
        ResponseEntity<BookDTO> responseEntity = bookController.addBook(bookDTO);
        Assertions.assertNotNull(responseEntity.getBody().getBookId());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario getBook")
    void testOneBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Dummy add");
        BookDTO getBook = new BookDTO();
        getBook.setBookId(1L);
        Mockito.when(bookService.getBook()).thenReturn(getBook);
        ResponseEntity<BookDTO> responseEntity = bookController.getBook();
        Assertions.assertNotNull(responseEntity.getBody().getBookId());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

    }
    @Test
    @DisplayName("test scenario to delete a book ")
    void testdelete()
    {
        Long bookId=1L;
        //  Mockito.when(bookService.deleteBook(bookId));
        HttpStatus status=bookController.deleteBook(bookId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,status.getClass());
    }
}