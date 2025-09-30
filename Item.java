public abstract class Item {
    private int id;
    private String type;
    private String title;
    private String author;
    private String publisher;
    private int publication_year;
    public Item(int id,String type,String title, String author, String publisher, int publication_year) {
        this.id= id;
        this.type= type;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publication_year = publication_year;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publication_year;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getType() {
        return type;
    }
   

    public String info() {
        return "ID: " + id + ", Tipo: " + type +", Title: " + title + ", Author: " + author + ", Publisher: " + publisher + ", Year: " + publication_year;
    }

}



 

 