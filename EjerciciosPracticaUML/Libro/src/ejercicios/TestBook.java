package ejercicios;

import java.util.ArrayList;
import java.util.Scanner;

public class TestBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Book> libros = new ArrayList<>();

        System.out.println("=== GESTIÓN DE LIBROS Y AUTORES ===");

        while (true) {
            System.out.println("\n1. Registrar libro");
            System.out.println("2. Ver libros");
            System.out.println("3. Modificar precio o cantidad de un libro");
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
                    System.out.print("Ingrese título del libro: ");
                    String titulo = sc.nextLine();

                    System.out.print("Ingrese nombre del autor: ");
                    String nombreAutor = sc.nextLine();

                    System.out.print("Ingrese email del autor: ");
                    String email = sc.nextLine();

                    System.out.print("Ingrese género del autor (m/f): ");
                    char genero = sc.nextLine().charAt(0);

                    Author autor = new Author(nombreAutor, email, genero);

                    System.out.print("Ingrese precio del libro: ");
                    double precio = sc.nextDouble();

                    System.out.print("Ingrese cantidad: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();

                    libros.add(new Book(titulo, autor, precio, cantidad));
                    System.out.println("Libro registrado con éxito.");
                    break;

                case 2:
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        System.out.println("\n=== Lista de Libros ===");
                        for (Book b : libros) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese título del libro a modificar: ");
                    String buscar = sc.nextLine();
                    boolean encontrado = false;

                    for (Book b : libros) {
                        if (b.getName().equalsIgnoreCase(buscar)) {
                            System.out.println("Libro encontrado: " + b);

                            System.out.print("Nuevo precio: ");
                            double nuevoPrecio = sc.nextDouble();

                            System.out.print("Nueva cantidad: ");
                            int nuevaCantidad = sc.nextInt();
                            sc.nextLine();

                            b.setPrice(nuevoPrecio);
                            b.setQty(nuevaCantidad);
                            System.out.println("Datos actualizados.");
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Libro no encontrado.");
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
