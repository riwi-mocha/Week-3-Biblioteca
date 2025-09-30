import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    //----------------------------------------------------------------------------------------------------------//
    // JDBC URL, username and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/adrian?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "adrian";
    // ----------------------------------------------------------------------------------------------------------//
    // Listar Todos los items
    public static void getItems() {
        String sql = "SELECT * FROM items";
        try (
                Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            System.out.println("\nLista de items:");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("type") + " - " +
                                rs.getString("title") + " - " +
                                rs.getString("author") + " - " +
                                rs.getString("publisher") + " - " +
                                rs.getString("publication_year")+ " - " +
                                rs.getString("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------//
    // Agregar un nuevo item
    public static void insertItem(int id,String type,String title, String author, String publisher, int publication_year) {
        String sql = "INSERT INTO items (id, type, title, author, publisher, publication_year) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, type);
            pstmt.setString(3, title);
            pstmt.setString(4, author);
            pstmt.setString(5, publisher);
            pstmt.setInt(6, publication_year);
            pstmt.executeUpdate();
            System.out.println("\nNuevo item agregado exitosamente: ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------//
    // Buscar un item por ID
    public static void getItem(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (
                Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nItem:");
            if (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " - " +
                                rs.getString("type") + " - " +
                                rs.getString("title") + " - " +
                                rs.getString("author") + " - " +
                                rs.getString("publisher") + " - " +
                                rs.getInt("publication_year") + " - " +
                                rs.getTimestamp("created_at"));
            } else {
                System.out.println("No se encontro un item con id = " + id);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ----------------------------------------------------------------------------------------------------------//
    // Metodo principal nuevo item
    public static void main(String[] args) {
        getItem(2);

        Scanner scanner = new Scanner(System.in);
            System.out.println("\nAgregar un nuevo item a la biblioteca\n");
            int id= 0;
            do {
                try {
                    System.out.print("Ingrese ID (numero entero positivo): ");
                    id = Integer.parseInt(scanner.nextLine());
                    if (id <= 0) System.out.println("El ID debe ser un numero mayor que 0.");
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un numero entero positivo.");
                }
            } while (id == 0);

            String type = "";
            do {
                try {
                    System.out.print("Ingrese el tipo (1 = Libro, 2 = Revista): ");
                    String input = scanner.nextLine();
                    if (input.equals("1")) {
                        type = "BOOK";
                    } else if (input.equals("2")) {
                        type = "MAGAZINE";
                    } else {
                        System.out.println("Opcion invalida. Intente de nuevo.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: Opcion invalida. Intente de nuevo.");
                }
            } while (type.isEmpty());

            String title = "";
            do {
                try {
                    System.out.print("Ingrese el titulo: ");
                    title = scanner.nextLine().trim();
                    if (title.isEmpty()) System.out.println("Error: El titulo no puede estar vacío.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (title.isEmpty());

            String author = "";
            do {
                try {
                    System.out.print("Ingrese el autor: ");
                    author = scanner.nextLine().trim();
                    if (author.isEmpty()) {
                        System.out.println("Error: El autor no puede estar vacío.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (author.isEmpty());

            String publisher = "";
            do {
                try {
                    System.out.print("Ingrese la editorial: ");
                    publisher = scanner.nextLine().trim();
                    if (publisher.isEmpty()) System.out.println("Error: La editorial no puede estar vacía.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (publisher.isEmpty());

            int publicationYear=0;
            boolean valido = false;

            do {
                try {
                    System.out.print("Ingrese el año de publicacion (ej: 2023): ");
                    publicationYear = Integer.parseInt(scanner.nextLine());

                    if (publicationYear < 1000 || publicationYear > 2025) {
                        System.out.println("Error: El año debe estar entre 1000 y 2025.");
                    }else {
                        valido = true; // es válido
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ser un numero valido.");
                    valido = false; // si no es número, repetimos
                }
            } while (!valido);
            
            
            Item newItem = null;
            if (type.equals("BOOK")) {
                newItem = new Book(id,type,title, author, publisher, publicationYear);
            }else if (type.equals("MAGAZINE")) {
                newItem = new Magazine(id,type,title, author, publisher, publicationYear);
                
            }
            scanner.close();
    //----------------------------------------------------------------------------------------------------------//
            // probar conexion y los metodos
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                insertItem(newItem.getId(),newItem.getType(),newItem.getTitle(), newItem.getAuthor(), newItem.getPublisher(), newItem.getPublicationYear());
                getItems();
                getItem(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}
