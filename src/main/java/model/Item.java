package model;

import java.time.LocalDateTime;

public abstract class Item {
    private Long materialId;           // material_id (PK, BIGINT)
    private String type;               // type (ENUM: BOOK, MAGAZINE)
    private String title;              // title (VARCHAR)
    private String author;             // author (VARCHAR)
    private String publisher;          // publisher (VARCHAR)
    private Integer publicationYear;   // publication_year (SMALLINT)
    private LocalDateTime createdAt;   // created_at (TIMESTAMP)

    // Constructor vacío
    public Item() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor con datos básicos
    public Item(String type, String title, String author, String publisher, Integer publicationYear) {
        this();
        this.type = type;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
    }

    // Getters y Setters
    public Long getMaterialId() { return materialId; }
    public void setMaterialId(Long materialId) { this.materialId = materialId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Métodos de validación
    public boolean isValidTitle() {
        return title != null && !title.trim().isEmpty();
    }

    public boolean isValidAuthor() {
        return author != null && !author.trim().isEmpty();
    }

    public boolean isValidPublisher() {
        return publisher != null && !publisher.trim().isEmpty();
    }

    public boolean isValidYear() {
        return publicationYear != null && publicationYear > 0 && publicationYear <= LocalDateTime.now().getYear();
    }

    // Método abstracto que las subclases deben implementar
    public abstract String getTypeDescription();

    // Método para mostrar información
    public String info() {
        return String.format("ID: %d, Tipo: %s, Título: %s, Autor: %s, Editorial: %s, Año: %d",
                           materialId, getTypeDescription(), title, author, publisher, publicationYear);
    }

    @Override
    public String toString() {
        return String.format("Item{materialId=%d, type='%s', title='%s', author='%s', publisher='%s', year=%d}",
                           materialId, type, title, author, publisher, publicationYear);
    }
}