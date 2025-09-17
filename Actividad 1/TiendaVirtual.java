package paquetePrimerProyecto;

import java.util.Scanner;

public class TiendaVirtual {
    static final int DESCUENTO_ROPA = 10;
    static final int DESCUENTO_TECNOLOGIA = 20;
    static final int DESCUENTO_ALIMENTOS = 50;
    static final int DESCUENTO_ADICIONAL = 10; 
    static final double UMBRAL = 500000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de productos que desea comprar: ");
        int cantidad = sc.nextInt();

        while (cantidad < 1) {
            System.out.println("La cantidad de productos debe ser al menos 1.");
            System.out.print("Ingrese nuevamente la cantidad de productos: ");
            cantidad = sc.nextInt();
        }

        String[] nombres = new String[cantidad];
        int[] tipos = new int[cantidad];
        double[] precios = new double[cantidad];
        double[] preciosFinales = new double[cantidad];

        for (int i = 0; i < cantidad; i++) {
            sc.nextLine();
            System.out.println("\nProducto #" + (i + 1));

            System.out.print("Nombre: ");
            nombres[i] = sc.nextLine();

            System.out.print("Tipo (1=ropa, 2=tecnologia, 3=alimentos): ");
            tipos[i] = sc.nextInt();
            while (tipos[i] < 1 || tipos[i] > 3) {
                System.out.println("Tipo inválido.");
                System.out.print("Tipo (1=ropa, 2=tecnologia, 3=alimentos): ");
                tipos[i] = sc.nextInt();
            }

            System.out.print("Precio: ");
            precios[i] = sc.nextDouble();
            while (precios[i] < 0) {
                System.out.println("El precio no puede ser negativo.");
                System.out.print("Precio: ");
                precios[i] = sc.nextDouble();
            }

            int porcDescuento = 0;
            switch (tipos[i]) {
                case 1: porcDescuento = DESCUENTO_ROPA; break;
                case 2: porcDescuento = DESCUENTO_TECNOLOGIA; break;
                case 3: porcDescuento = DESCUENTO_ALIMENTOS; break;
            }

            preciosFinales[i] = precios[i] - (precios[i] * porcDescuento / 100.0);
        }

        double totalSinDescuento = 0;
        double totalConDescuento = 0;

        System.out.println("\nResumen de compras:");
        for (int i = 0; i < cantidad; i++) {
            int porc = 0;
            switch (tipos[i]) {
                case 1: porc = DESCUENTO_ROPA; break;
                case 2: porc = DESCUENTO_TECNOLOGIA; break;
                case 3: porc = DESCUENTO_ALIMENTOS; break;
            }

            System.out.printf("%d. %s | Tipo: %d | Precio: %.2f | Descuento: %d%% | Final: %.2f%n",
                    i + 1, nombres[i], tipos[i], precios[i], porc, preciosFinales[i]);

            totalSinDescuento += precios[i];
            totalConDescuento += preciosFinales[i];
        }

        if (totalConDescuento > UMBRAL) {
            double descuentoExtra = totalConDescuento * DESCUENTO_ADICIONAL / 100.0;
            totalConDescuento -= descuentoExtra;
            System.out.printf("%nSe aplicó un descuento adicional del %d%% por superar $%.0f.%n",
                    DESCUENTO_ADICIONAL, UMBRAL);
        }

        double ahorro = totalSinDescuento - totalConDescuento;

        System.out.printf("%nTotal sin descuentos: %.2f%n", totalSinDescuento);
        System.out.printf("Total con descuentos: %.2f%n", totalConDescuento);
        System.out.printf("Ahorro total: %.2f%n", ahorro);

        sc.close();
    }
}
