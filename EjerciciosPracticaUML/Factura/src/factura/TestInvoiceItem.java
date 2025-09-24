package factura;

import java.util.ArrayList;
import java.util.Scanner;

public class TestInvoiceItem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<InvoiceItem> factura = new ArrayList<>();

        System.out.println("===== SISTEMA DE FACTURACIÓN =====");

        boolean agregarMas = true;
        while (agregarMas) {
            System.out.print("\nIngrese el ID del producto: ");
            String id = sc.nextLine();

            System.out.print("Ingrese la descripción del producto: ");
            String desc = sc.nextLine();

            System.out.print("Ingrese la cantidad: ");
            int qty = sc.nextInt();

            System.out.print("Ingrese el precio unitario: ");
            double unitPrice = sc.nextDouble();
            sc.nextLine(); // limpiar buffer

            // Crear ítem y añadirlo a la factura
            InvoiceItem item = new InvoiceItem(id, desc, qty, unitPrice);
            factura.add(item);

            System.out.print("\n¿Desea agregar otro producto? (s/n): ");
            String respuesta = sc.nextLine();
            if (!respuesta.equalsIgnoreCase("s")) {
                agregarMas = false;
            }
        }

        // Mostrar factura completa
        System.out.println("\n===== FACTURA FINAL =====");
        double totalFactura = 0;
        for (InvoiceItem item : factura) {
            System.out.println(item);
            System.out.println("   Subtotal: " + item.getTotal());
            totalFactura += item.getTotal();
        }

        System.out.println("---------------------------");
        System.out.println("TOTAL A PAGAR: " + totalFactura);

        sc.close();
    }
}
