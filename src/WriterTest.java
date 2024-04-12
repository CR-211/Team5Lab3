import java.util.ArrayList;
import java.util.HashSet;

public class WriterTest {

    @Test
    public void testUniqueBooksAdded() {
        Writer writer = new Writer("Test Writer");
        writer.start();
        try {
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> books = Library.library;

        HashSet<String> uniqueBooks = new HashSet<>(books);
        assertEquals(books.size(), uniqueBooks.size());
    }


    private void assertEquals(int size, int size2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }


    @Test
    public void testAddingBooksToEmptyLibrary() {
        Library.library.clear();

        Writer writer = new Writer("Test Writer");
        writer.start();
        try {
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> books = Library.library;

        // Verificăm că numărul de cărți adăugate în bibliotecă este corect
        assertEquals(Library.books, books.size());
    }
}