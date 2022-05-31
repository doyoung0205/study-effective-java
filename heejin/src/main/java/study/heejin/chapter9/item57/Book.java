package study.heejin.chapter9.item57;

public class Book {

    private final String author;
    private final String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{author='" + author + '\'' + ", title='" + title + '\'' + '}';
    }
}
