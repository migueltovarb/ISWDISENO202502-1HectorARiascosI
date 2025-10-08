package veterinario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Duenio> dueños = new ArrayList<>();
    static ArrayList<Mascota> mascotas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- Menú Veterinaria ---");
            System.out.println("1. Registrar dueño");
            System.out.println("2. Registrar mascota");
            System.out.println("3. Registrar control veterinario");
            System.out.println("4. Consultar historial de mascota");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrarDuenio();
                case 2 -> registrarMascota();
                case 3 -> registrarControl();
                case 4 -> consultarHistorial();
                case 5 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    public static void registrarDuenio() {
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Duenio d = new Duenio(nombre, documento, telefono);
        dueños.add(d);
        System.out.println("Dueño registrado.");
    }

    public static void registrarMascota() {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = sc.nextLine();
        System.out.print("Especie: ");
        String especie = sc.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(sc.nextLine());

        System.out.print("Documento del dueño: ");
        String doc = sc.nextLine();
        Duenio duenioEncontrado = null;
        for (Duenio d : dueños) {
            if (d.getDocumento().equals(doc)) {
                duenioEncontrado = d;
                break;
            }
        }

        if (duenioEncontrado == null) {
            System.out.println("Dueño no encontrado.");
            return;
        }

       
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombreMascota) && m.getDuenio().getDocumento().equals(doc)) {
                System.out.println("Ya existe una mascota con ese nombre para este dueño.");
                return;
            }
        }

        Mascota m = new Mascota(nombreMascota, especie, edad, duenioEncontrado);
        mascotas.add(m);
        System.out.println("Mascota registrada.");
    }

    public static void registrarControl() {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = sc.nextLine();
        System.out.print("Documento del dueño: ");
        String doc = sc.nextLine();

        Mascota mascota = buscarMascota(nombreMascota, doc);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }

        System.out.print("Fecha (dd/mm/aaaa): ");
        String fecha = sc.nextLine();

        System.out.println("Tipo de control:");
        for (TipoControl tipo : TipoControl.values()) {
            System.out.println("- " + tipo);
        }
        System.out.print("Selecciona tipo: ");
        TipoControl tipo = TipoControl.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Observaciones: ");
        String obs = sc.nextLine();

        ControlVeterinario cv = new ControlVeterinario(fecha, tipo, obs);
        mascota.agregarControl(cv);
        System.out.println("Control registrado.");
    }

    public static void consultarHistorial() {
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = sc.nextLine();
        System.out.print("Documento del dueño: ");
        String doc = sc.nextLine();

        Mascota mascota = buscarMascota(nombreMascota, doc);
        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }

        System.out.println("\n--- Datos de la mascota ---");
        System.out.println(mascota);
        System.out.println("\n--- Controles registrados ---");

        if (mascota.getControles().isEmpty()) {
            System.out.println("No hay controles registrados.");
        } else {
            for (ControlVeterinario c : mascota.getControles()) {
                System.out.println(c);
            }
        }
    }

    public static Mascota buscarMascota(String nombre, String docDueño) {
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(nombre) && m.getDuenio().getDocumento().equals(docDueño)) {
                return m;
            }
        }
        return null;
    }
}
