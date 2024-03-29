import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {

    @Test
    public void testReaderConstructor() {
        Reader reader = new Reader("TestReader");
        assertEquals("TestReader", reader.name);
        assertNotNull(reader.readBooks);
    }

    @Test
    public void testAddBookToReadList() {
        Reader reader = new Reader("TestReader");
        reader.readBooks.add("Book 1"); // Adăugăm manual o carte în lista de cărți citite
        assertTrue(reader.readBooks.contains("Book 1"), "Cartea ar trebui să fie adăugată în lista de cărți citite");
    }

    @Test
    public void testAddDuplicateBookToReadList() {
        Reader reader = new Reader("TestReader");
        reader.readBooks.add("Book 1"); // Adăugăm manual o carte în lista de cărți citite
        reader.readBooks.add("Book 1"); // Adăugăm din nou aceeași carte
        assertEquals(2, reader.readBooks.size(), "Nu ar trebui să se permită adăugarea duplicatelor în lista de cărți citite");
    }

}