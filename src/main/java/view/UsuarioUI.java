package view;

import model.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioUI {
    private Scanner scanner = new Scanner(System.in);


    public Usuario capturarDatosUsuario() {
        System.out.println("\n=== REGISTER NEW USER ===");

        
        String documentId;
        do {
            System.out.print("Document ID: ");
            documentId = scanner.nextLine().trim();
            if (documentId.length() < 7) {
                System.out.println("Document must have at least 7 characters");
            }
        } while (documentId.length() < 7);

        
        String fullName;
        do {
            System.out.print("Full name: ");
            fullName = scanner.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("Name cannot be empty");
            }
        } while (fullName.isEmpty());

        
        String email = null;
        while (true) {
            System.out.print("Email (optional): ");
            String emailInput = scanner.nextLine().trim();
            if (emailInput.isEmpty()) {
                break; 
            }
            if (emailInput.contains("@") && emailInput.contains(".")) {
                email = emailInput;
                break; 
            } else {
                System.out.println("Invalid email (must contain @)");
            }
        }

        
        String phone = null;
        while (true) {
            System.out.print("Phone (optional): ");
            String phoneInput = scanner.nextLine().trim();
            if (phoneInput.isEmpty()) {
                break; 

            }
            if (phoneInput.length() >= 7) {
                phone = phoneInput;
                break; 
            } else {
                System.out.println("Phone must have at least 7 characters");
            }
        }

        if (email != null && phone != null) {
            return new Usuario(documentId, fullName, email, phone);
        } else {
            Usuario usuario = new Usuario(documentId, fullName);
            if (email != null) usuario.setEmail(email);
            if (phone != null) usuario.setPhone(phone);
            return usuario;
        }
    }

    public Long capturarId() {
        System.out.print("Enter user ID: ");
        return scanner.nextLong();
    }

    public String capturarDocumento() {
        System.out.print("Enter document ID: ");
        return scanner.nextLine().trim();
    }

    public String capturarEmail() {
        System.out.print("Enter email: ");
        return scanner.nextLine().trim();
    }

    public void mostrarUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("User not found.");
            return;
        }

       
        System.out.println("ID: " + usuario.getUserId());
        System.out.println("Document: " + usuario.getDocumentId());
        System.out.println("Name: " + usuario.getFullName());
        System.out.println("Email: " + (usuario.getEmail() != null ? usuario.getEmail() : "Not specified"));
        System.out.println("Phone: " + (usuario.getPhone() != null ? usuario.getPhone() : "Not specified"));
        System.out.println("Registration date: " + usuario.getCreatedAt());
    }

    public void mostrarListaUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        System.out.printf("%-5s %-15s %-25s %-25s %-15s%n",
                         "ID", "DOCUMENT", "NAME", "EMAIL", "PHONE");
        System.out.println("─".repeat(90));

        for (Usuario usuario : usuarios) {
            System.out.printf("%-5d %-15s %-25s %-25s %-15s%n",
                usuario.getUserId(),
                usuario.getDocumentId(),
                usuario.getFullName(),
                usuario.getEmail() != null ? usuario.getEmail() : "N/A",
                usuario.getPhone() != null ? usuario.getPhone() : "N/A"
            );
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }

    public void mostrarExito(String mensaje) {
        System.out.println("OK" + mensaje);
    }

    public boolean confirmarAccion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }
}