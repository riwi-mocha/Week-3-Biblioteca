// Declaramos que esta clase pertenece a model
package model;

import java.time.LocalDateTime;

public class Usuario {
    // Campos que coinciden EXACTAMENTE con la tabla users
    private Long userId;           // user_id (PK, BIGINT, autoincrement)
    private String documentId;     // document_id (VARCHAR, único)
    private String fullName;       // full_name (VARCHAR)
    private String email;          // email (VARCHAR, único)
    private String phone;          // phone (VARCHAR)
    private LocalDateTime createdAt; // created_at (TIMESTAMP)

    // Constructor vacío
    public Usuario() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor con datos básicos
    public Usuario(String documentId, String fullName) {
        this();
        this.documentId = documentId;
        this.fullName = fullName;
    }

    // Constructor completo
    public Usuario(String documentId, String fullName, String email, String phone) {
        this(documentId, fullName);
        this.email = email;
        this.phone = phone;
    }

    // Getters y Setters que coinciden con los campos de DB
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDocumentId() { return documentId; }
    public void setDocumentId(String documentId) { this.documentId = documentId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Métodos de validación
    public boolean isValidDocumentId() {
        return documentId != null && documentId.trim().length() >= 7;
    }

    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isValidFullName() {
        return fullName != null && !fullName.trim().isEmpty();
    }

    public boolean isValidPhone() {
        return phone != null && phone.trim().length() >= 7;
    }

    // Método para mostrar información
    @Override
    public String toString() {
        return String.format("Usuario{userId=%d, documentId='%s', fullName='%s', email='%s', phone='%s'}",
                           userId, documentId, fullName, email, phone);
    }
}