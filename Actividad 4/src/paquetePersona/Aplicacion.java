package paquetePersona;

import java.util.Scanner;

public class Aplicacion {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedir datos por teclado
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese edad: ");
        int edad = sc.nextInt();

        System.out.print("Ingrese sexo (H/M): ");
        char sexo = sc.next().charAt(0);

        System.out.print("Ingrese peso (kg): ");
        double peso = sc.nextDouble();

        System.out.print("Ingrese altura (m): ");
        double altura = sc.nextDouble();

        // Crear los 3 objetos
        Persona p1 = new Persona(nombre, edad, sexo, peso, altura);




        // Mostrar resultados
        mostrarInfo(p1);

        sc.close();
    }

    // Método auxiliar para imprimir info de cada persona
    private static void mostrarInfo(Persona persona) {
        // Estado de peso
        int imc = persona.calcularIMC();
        String estadoPeso;
        switch (imc) {
            case Persona.BAJO_PESO:
                estadoPeso = "Bajo peso";
                break;
            case Persona.PESO_IDEAL:
                estadoPeso = "Peso ideal";
                break;
            case Persona.SOBREPESO:
                estadoPeso = "Sobrepeso";
                break;
            default:
                estadoPeso = "Desconocido";
        }

        // Mayor de edad
        String mayorEdad = persona.esMayorDeEdad() ? "Sí" : "No";

        // Mostrar resultados
        System.out.println("\n--- Información de la persona ---");
        System.out.println(persona.toString());
        System.out.println("Estado de peso: " + estadoPeso);
        System.out.println("¿Es mayor de edad?: " + mayorEdad);
    }
}