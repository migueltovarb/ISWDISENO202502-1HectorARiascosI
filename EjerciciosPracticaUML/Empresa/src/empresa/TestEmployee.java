package empresa;

import java.util.Scanner;

public class TestEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== CREACIÓN DE EMPLEADO =====");

        System.out.print("Ingrese ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingrese primer nombre: ");
        String firstName = sc.nextLine();

        System.out.print("Ingrese apellido: ");
        String lastName = sc.nextLine();

        System.out.print("Ingrese salario mensual: ");
        int salary = sc.nextInt();

        // Crear empleado a partir de datos ingresados
        Employee e = new Employee(id, firstName, lastName, salary);

        System.out.println("\n--- DATOS DEL EMPLEADO ---");
        System.out.println(e);
        System.out.println("ID: " + e.getID());
        System.out.println("Nombre completo: " + e.getName());
        System.out.println("Salario mensual: $" + e.getSalary());
        System.out.println("Salario anual: $" + e.getAnnualSalary());

        // Aumento de salario
        System.out.print("\nIngrese porcentaje de aumento: ");
        int porcentaje = sc.nextInt();

        e.raiseSalary(porcentaje);

        System.out.println("\n--- DESPUÉS DEL AUMENTO ---");
        System.out.println("Nuevo estado: " + e);

        sc.close();
    }
}

