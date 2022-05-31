package study.heejin.chapter9.item57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library implements Iterable<Book> {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Iterator iterator() {
        return books.iterator();
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("도스토예프스키", "카리마조프가의 형제들1"));
        library.addBook(new Book("도스토예프스키", "카리마조프가의 형제들2"));
        library.addBook(new Book("도스토예프스키", "카리마조프가의 형제들3"));

        for (Book book : library) {
            System.out.println(book);
        }

        System.out.println("----------------");

        Iterator<Book> it = library.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
