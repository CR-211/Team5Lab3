import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryTest {

    @Test
    public void testInitialization() {
        Library library = new Library(2, 3, 5);

        // Verificăm că numărul de scriitori, cititori și cărți este inițializat corect
        assertEquals(2, library.writers.length);
        assertEquals(3, library.readers.length);
        assertEquals(5, Library.books);
    }

    @Test
    public void testStart() {
        Library library = new Library(1, 1, 2);

        // Verificăm că biblioteca pornește corect
        library.start();

        // Verificăm că interfața grafică este inițializată și afișată corect (verificarea manuală)
    }
}