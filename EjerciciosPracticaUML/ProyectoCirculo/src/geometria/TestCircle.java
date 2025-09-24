package geometria;

import java.util.Scanner;

public class TestCircle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== CÁLCULO DE CÍRCULO =====");

        System.out.print("Ingrese el radio del círculo: ");
        double radio = sc.nextDouble();

        // Crear círculo con el radio ingresado
        Circle c = new Circle(radio);

        System.out.println("\n--- DATOS DEL CÍRCULO ---");
        System.out.println(c);
        System.out.println("Radio: " + c.getRadius());
        System.out.println("Área: " + c.getArea());
        System.out.println("Circunferencia: " + c.getCircumference());

        // Cambiar radio de forma interactiva
        System.out.print("\nIngrese un nuevo valor de radio para actualizar: ");
        double nuevoRadio = sc.nextDouble();

        c.setRadius(nuevoRadio);

        System.out.println("\n--- DESPUÉS DE ACTUALIZAR EL RADIO ---");
        System.out.println(c);
        System.out.println("Área: " + c.getArea());
        System.out.println("Circunferencia: " + c.getCircumference());

        sc.close();
    }
}
