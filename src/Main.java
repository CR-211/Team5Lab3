import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("unused")
class Library {
    static int books;
    Writer[] writers;
    Reader[] readers;

    static ArrayList<String> library = new ArrayList<>();
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
    static final Lock writeLock = rwl.writeLock();
    static final Lock readLock = rwl.readLock();

    public Library(int writers, int readers, int books) {
        this.writers = new Writer[writers];
        this.readers = new Reader[readers];
        Library.books = books;

        for (int i = 0; i < writers; i++) {
            this.writers[i] = new Writer("Writer " + (i + 1));
        }
        for (int i = 0; i < readers; i++) {
            this.readers[i] = new Reader("Reader " + (i + 1));
        }
    }

    public void start() {
        JFrame frame = new JFrame("Library Activity");
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        for (Writer writer : this.writers) {
            writer.setTextArea(textArea);
            writer.start();
        }
        for (Reader reader : this.readers) {
            reader.setTextArea(textArea);
            reader.start();
        }
    }
}

class Writer extends Thread {
    String name;
    ArrayList<String> bookList = new ArrayList<>(Arrays.asList("Book 1", "Book 2", "Book 3", "Book 4", "Book 5",
            "Book 6", "Book 7", "Book 8"));

    public final Lock writeLock = Library.writeLock;
    ArrayList<String> library = Library.library;
    ArrayList<String> writtenBooks = new ArrayList<>();
    static int count = 0;
    private JTextArea textArea;

    public Writer(String name) {
        this.name = name;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        while (library.size() < Library.books) {
            try {
                writeLock.lock();
                if (library.size() < Library.books) {
                    String randomBook = bookList.get(count);
                    if (!library.contains(randomBook)) {
                        sleep(1000);
                        library.add(randomBook);
                        writtenBooks.add(randomBook);
                        updateGUI(name + " wrote " + randomBook);
                        count++;
                        sleep(100);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }
            if (library.size() == Library.books) {
                updateGUI(name + " book list: \n" + writtenBooks);
            }
        }
    }

    private void updateGUI(String message) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(message + "\n");
        });
    }
}

class Reader extends Thread {
    public final Lock readLock = Library.readLock;
    ArrayList<String> readBooks = new ArrayList<>();
    ArrayList<String> library = Library.library;
    String name;

    private JTextArea textArea;

    public Reader(String name) {
        this.name = name;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        while (readBooks.size() < Library.books) {
            try {
                if (Library.rwl.isWriteLocked()) {
                    System.out.println(name + ": writer is in library");
                }
                readLock.lock();
                int random = (int) (Math.random() * library.size());
                if (random < library.size()) {
                    String randomBook = library.get(random);
                    if (readBooks.size() < Library.books) {
                        if (!readBooks.contains(randomBook)) {
                            sleep(300);
                            readBooks.add(randomBook);
                            updateGUI(name + " read book " + randomBook);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
        }
        updateGUI(name + " finished reading \n" + readBooks);
    }

    private void updateGUI(String message) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(message + "\n");
        });
    }
}

public class Main {
    public static void main(String[] args) {
        final int writers = 5;
        final int readers = 10;
        final int books = 8;

        Library library = new Library(writers, readers, books);
        library.start();
    }
}

