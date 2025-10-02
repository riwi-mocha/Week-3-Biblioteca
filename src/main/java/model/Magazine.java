package model;

public class Magazine extends Item {

    public Magazine() {
        super();
        setType("MAGAZINE");
    }

    public Magazine(String title, String author, String publisher, Integer publicationYear) {
        super("MAGAZINE", title, author, publisher, publicationYear);
    }

    @Override
    public String getTypeDescription() {
        return "Revista";
    }
}