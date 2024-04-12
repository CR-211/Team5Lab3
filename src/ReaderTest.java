import java.util.ArrayList;

public class ReaderTest {

    @Test
    public void testReaderConstructor() {
        Reader reader = new Reader("TestReader");
        assertEquals("TestReader", reader.name);
        assertNotNull(reader.readBooks);
    }

    private void assertNotNull(ArrayList<String> readBooks) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertNotNull'");
    }

    private void assertEquals(String string, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    @Test
    public void testAddBookToReadList() {
        Reader reader = new Reader("TestReader");
        reader.readBooks.add("Book 1"); // Adăugăm manual o carte în lista de cărți citite
        assertEquals(reader.readBooks.contains("Book 1"), "Cartea ar trebui să fie adăugată în lista de cărți citite");
    }

    private void assertEquals(boolean contains, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

    @Test
    public void testAddDuplicateBookToReadList() {
        Reader reader = new Reader("TestReader");
        reader.readBooks.add("Book 1"); // Adăugăm manual o carte în lista de cărți citite
        reader.readBooks.add("Book 1"); // Adăugăm din nou aceeași carte
        assertEquals(2, reader.readBooks.size(), "Nu ar trebui să se permită adăugarea duplicatelor în lista de cărți citite");
    }

    private void assertEquals(int i, int size, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
    }

}