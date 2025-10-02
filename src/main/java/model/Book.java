package model;

public class Book extends Item {

    public Book() {
        super();
        setType("BOOK");
    }

    public Book(String title, String author, String publisher, Integer publicationYear) {
        super("BOOK", title, author, publisher, publicationYear);
    }

    @Override
    public String getTypeDescription() {
        return "Libro";
    }
}