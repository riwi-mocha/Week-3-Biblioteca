import controller.UsuarioController;
import controller.MaterialController;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioController usuarioController = new UsuarioController();
    private static MaterialController materialController = new MaterialController();

    public static void main(String[] args) {

        System.out.println("    LIBRARY MANAGEMENT SYSTEM");

        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. User Management");
            System.out.println("2. Materials Management");
            System.out.println("3. Loans Management (Coming Soon)");
            System.out.println("4. Reports (Coming Soon)");
            System.out.println("0. Exit System");
            System.out.print("Select an option: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    gestionarUsuarios();
                    break;
                case 2:
                    gestionarMateriales();
                    break;
                case 3:
                    System.out.println("Loans Management - Coming Soon");
                    break;
                case 4:
                    System.out.println("Reports - Coming Soon");
                    break;
                case 0:
                    System.out.println("Thank you for using the Library System!");
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (opcion != 0);
    }

    private static void gestionarUsuarios() {
        int opcion;
        do {
            System.out.println("\n=== USER MANAGEMENT ===");
            System.out.println("1. Register new user");
            System.out.println("2. Search user by ID");
            System.out.println("3. Search user by document");
            System.out.println("4. Search user by email");
            System.out.println("5. List all users");
            System.out.println("6. Update user");
            System.out.println("7. Delete user");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    usuarioController.registrarUsuario();
                    break;
                case 2:
                    usuarioController.buscarUsuarioPorId();
                    break;
                case 3:
                    usuarioController.buscarUsuarioPorDocumento();
                    break;
                case 4:
                    usuarioController.buscarUsuarioPorEmail();
                    break;
                case 5:
                    usuarioController.listarTodosLosUsuarios();
                    break;
                case 6:
                    usuarioController.actualizarUsuario();
                    break;
                case 7:
                    usuarioController.eliminarUsuario();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (opcion != 0);
    }

    private static void gestionarMateriales() {
        int opcion;
        do {
            System.out.println("\n=== MATERIALS MANAGEMENT ===");
            System.out.println("1. Register new material");
            System.out.println("2. Search material by ID");
            System.out.println("3. Search materials by title");
            System.out.println("4. Search materials by author");
            System.out.println("5. Search materials by type");
            System.out.println("6. List all materials");
            System.out.println("7. List available materials");
            System.out.println("8. Update material");
            System.out.println("9. Delete material");
            System.out.println("0. Back to main menu");
            System.out.print("Select an option: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opcion) {
                case 1:
                    materialController.registrarMaterial();
                    break;
                case 2:
                    materialController.buscarMaterialPorId();
                    break;
                case 3:
                    materialController.buscarMaterialesPorTitulo();
                    break;
                case 4:
                    materialController.buscarMaterialesPorAutor();
                    break;
                case 5:
                    materialController.buscarMaterialesPorTipo();
                    break;
                case 6:
                    materialController.listarTodosLosMateriales();
                    break;
                case 7:
                    materialController.listarMaterialesDisponibles();
                    break;
                case 8:
                    materialController.actualizarMaterial();
                    break;
                case 9:
                    materialController.eliminarMaterial();
                    break;
                case 0:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (opcion != 0);
    }


    private static void cargarDatosDePrueba() {
        System.out.println("Cargando datos de prueba...");

        // Aquí se pueden agregar usuarios de prueba si es necesario

        System.out.println("Datos de prueba cargados.");
    }
}