package view;

import model.*;
import java.util.List;
import java.util.Scanner;

public class MaterialUI {
    private Scanner scanner = new Scanner(System.in);

    public Item capturarDatosLibro() {
        System.out.println("\n=== REGISTER NEW BOOK ===");

        String titulo = capturarTitulo();
        String autor = capturarAutor();
        String editorial = capturarEditorial();
        Integer año = capturarAño();

        return new Book(titulo, autor, editorial, año);
    }

    public Item capturarDatosRevista() {
        System.out.println("\n=== REGISTER NEW MAGAZINE ===");

        String titulo = capturarTitulo();
        String autor = capturarAutor();
        String editorial = capturarEditorial();
        Integer año = capturarAño();

        return new Magazine(titulo, autor, editorial, año);
    }

    private String capturarTitulo() {
        String titulo;
        do {
            System.out.print("Title: ");
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("ERROR: Title cannot be empty");
            }
        } while (titulo.isEmpty());
        return titulo;
    }

    private String capturarAutor() {
        String autor;
        do {
            System.out.print("Author: ");
            autor = scanner.nextLine().trim();
            if (autor.isEmpty()) {
                System.out.println("ERROR: Author cannot be empty");
            }
        } while (autor.isEmpty());
        return autor;
    }

    private String capturarEditorial() {
        String editorial;
        do {
            System.out.print("Publisher: ");
            editorial = scanner.nextLine().trim();
            if (editorial.isEmpty()) {
                System.out.println("ERROR: Publisher cannot be empty");
            }
        } while (editorial.isEmpty());
        return editorial;
    }

    private Integer capturarAño() {
        Integer año = null;
        while (año == null) {
            System.out.print("Publication year: ");
            try {
                int añoInput = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                if (añoInput > 0 && añoInput <= java.time.LocalDateTime.now().getYear()) {
                    año = añoInput;
                } else {
                    System.out.println("ERROR: Invalid year");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Enter a valid number");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        return año;
    }

    public Long capturarId() {
        System.out.print("Enter material ID: ");
        return scanner.nextLong();
    }

    public String capturarTexto(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public int capturarOpcionTipo() {
        System.out.println("¿Qué tipo de material?");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.print("Seleccione (1-2): ");
        return scanner.nextInt();
    }

    public void mostrarMaterial(Item material) {
        if (material == null) {
            System.out.println("❌ Material no encontrado.");
            return;
        }

        System.out.println("\n=== INFORMACIÓN DEL MATERIAL ===");
        System.out.println("ID: " + material.getMaterialId());
        System.out.println("Tipo: " + material.getTypeDescription());
        System.out.println("Título: " + material.getTitle());
        System.out.println("Autor: " + material.getAuthor());
        System.out.println("Editorial: " + material.getPublisher());
        System.out.println("Año: " + material.getPublicationYear());
        System.out.println("Registrado: " + material.getCreatedAt());
    }

    public void mostrarListaMateriales(List<Item> materiales) {
        if (materiales.isEmpty()) {
            System.out.println("❌ No hay materiales registrados.");
            return;
        }

        System.out.println("\n=== LISTA DE MATERIALES ===");
        System.out.printf("%-5s %-10s %-30s %-25s %-20s %-6s%n",
                         "ID", "TIPO", "TÍTULO", "AUTOR", "EDITORIAL", "AÑO");
        System.out.println("─".repeat(100));

        for (Item material : materiales) {
            System.out.printf("%-5d %-10s %-30s %-25s %-20s %-6d%n",
                material.getMaterialId(),
                material.getTypeDescription(),
                truncate(material.getTitle(), 30),
                truncate(material.getAuthor(), 25),
                truncate(material.getPublisher(), 20),
                material.getPublicationYear()
            );
        }
    }

    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println("ERROR: " + error);
    }

    public void mostrarExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }

    public boolean confirmarAccion(String mensaje) {
        System.out.print(mensaje + " (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si");
    }
}