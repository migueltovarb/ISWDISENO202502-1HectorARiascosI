package empresa;

public class TestEmployee {
    public static void main(String[] args) {
        // Crear empleados
        Employee e1 = new Employee(1, "Juan", "Pérez", 2000);
        Employee e2 = new Employee(2, "Ana", "Gómez", 3000);

        // Imprimir información
        System.out.println(e1); // Employee[id=1, name=Juan Pérez, salary=2000]
        System.out.println("ID: " + e1.getID());
        System.out.println("Nombre: " + e1.getName());
        System.out.println("Salario mensual: " + e1.getSalary());
        System.out.println("Salario anual: " + e1.getAnnualSalary());

        // Aumentar salario
        e1.raiseSalary(10);
        System.out.println("Después de aumento (10%): " + e1);

        System.out.println("\n" + e2);
        e2.raiseSalary(20);
        System.out.println("Después de aumento (20%): " + e2);
    }
}
