package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class TestAuthor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Author> autores = new ArrayList<>();

        System.out.println("=== GESTIÓN DE AUTORES ===");

        while (true) {
            System.out.println("\n1. Crear autor");
            System.out.println("2. Ver autores");
            System.out.println("3. Cambiar email de un autor");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese email: ");
                    String email = sc.nextLine();

                    System.out.print("Ingrese género (m/f): ");
                    char genero = sc.nextLine().charAt(0);

                    autores.add(new Author(nombre, email, genero));
                    System.out.println("Autor creado con éxito.");
                    break;

                case 2:
                    System.out.println("\n=== Lista de Autores ===");
                    for (Author a : autores) {
                        System.out.println(a);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese nombre del autor a modificar: ");
                    String buscar = sc.nextLine();
                    boolean encontrado = false;

                    for (Author a : autores) {
                        if (a.getName().equalsIgnoreCase(buscar)) {
                            System.out.print("Ingrese nuevo email: ");
                            String nuevoEmail = sc.nextLine();
                            a.setEmail(nuevoEmail);
                            System.out.println("Email actualizado.");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Autor no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del sistema...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
