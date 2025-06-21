package javaaa;
import java.util.Arrays;
import java.util.Comparator;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Adding books (we'll add them in unsorted order first)
        library.addBook(3, "The Great Gatsby", "F. Scott Fitzgerald");
        library.addBook(1, "To Kill a Mockingbird", "Harper Lee");
        library.addBook(4, "1984", "George Orwell");
        library.addBook(2, "Pride and Prejudice", "Jane Austen");
        library.addBook(5, "The Hobbit", "J.R.R. Tolkien");
        
        // Display all books (unsorted)
        System.out.println("All Books (Unsorted):");
        library.displayBooks();
        
        // Linear search for a book by title
        System.out.println("\nLinear Search Results:");
        Book linearSearchResult = library.linearSearchByTitle("1984");
        System.out.println("Found by linear search: " + 
            (linearSearchResult != null ? linearSearchResult : "Not found"));
        
        // Sort books by title for binary search
        library.sortBooksByTitle();
        System.out.println("\nBooks after sorting by title:");
        library.displayBooks();
        
        // Binary search for a book by title
        System.out.println("\nBinary Search Results:");
        Book binarySearchResult = library.binarySearchByTitle("Pride and Prejudice");
        System.out.println("Found by binary search: " + 
            (binarySearchResult != null ? binarySearchResult : "Not found"));
        
        // Search for non-existent book
        System.out.println("\nSearching for non-existent book:");
        Book notFound = library.binarySearchByTitle("Moby Dick");
        System.out.println("Search result: " + 
            (notFound != null ? notFound : "Not found"));
    }
}

class Book {
    private int bookId;
    private String title;
    private String author;
    
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: \"" + title + "\", Author: " + author;
    }
}

class Library {
    private Book[] books;
    private int count;
    private static final int DEFAULT_CAPACITY = 10;
    
    public Library() {
        books = new Book[DEFAULT_CAPACITY];
        count = 0;
    }
    
    public void addBook(int bookId, String title, String author) {
        if (count == books.length) {
            // Resize the array if needed
            books = Arrays.copyOf(books, books.length * 2);
        }
        books[count++] = new Book(bookId, title, author);
    }
    
    // Linear search (Time Complexity: O(n))
    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }
    
    // Binary search (Time Complexity: O(log n))
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = count - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            
            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    
    // Sort books by title (Time Complexity: O(n log n))
    public void sortBooksByTitle() {
        Arrays.sort(books, 0, count, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
    }
    
    public void displayBooks() {
        if (count == 0) {
            System.out.println("No books in the library.");
            return;
        }
        
        for (int i = 0; i < count; i++) {
            System.out.println(books[i]);
        }
    }
}