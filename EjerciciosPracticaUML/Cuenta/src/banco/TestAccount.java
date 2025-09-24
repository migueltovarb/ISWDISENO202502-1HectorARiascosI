package banco;

import java.util.ArrayList;
import java.util.Scanner;

public class TestAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> cuentas = new ArrayList<>();

        System.out.println("=== BANCO - Gestión de Cuentas ===");

        while (true) {
            System.out.println("\n1. Crear cuenta");
            System.out.println("2. Ver cuentas");
            System.out.println("3. Acreditar dinero");
            System.out.println("4. Debitar dinero");
            System.out.println("5. Transferir dinero");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                sc.nextLine(); // limpiar buffer
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID: ");
                    String id = sc.nextLine();

                    System.out.print("Ingrese nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Ingrese saldo inicial: ");
                    int saldo = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    cuentas.add(new Account(id, nombre, saldo));
                    System.out.println("Cuenta creada con éxito.");
                    break;

                case 2:
                    System.out.println("\n=== Listado de Cuentas ===");
                    for (Account acc : cuentas) {
                        System.out.println(acc);
                    }
                    break;

                case 3:
                    System.out.print("Ingrese ID de la cuenta: ");
                    String idCredit = sc.nextLine();
                    System.out.print("Ingrese monto a acreditar: ");
                    int montoC = sc.nextInt();
                    sc.nextLine();
                    for (Account acc : cuentas) {
                        if (acc.getID().equals(idCredit)) {
                            acc.credit(montoC);
                            System.out.println("Nuevo saldo: " + acc.getBalance());
                        }
                    }
                    break;

                case 4:
                    System.out.print("Ingrese ID de la cuenta: ");
                    String idDebit = sc.nextLine();
                    System.out.print("Ingrese monto a debitar: ");
                    int montoD = sc.nextInt();
                    sc.nextLine();
                    for (Account acc : cuentas) {
                        if (acc.getID().equals(idDebit)) {
                            acc.debit(montoD);
                            System.out.println("Nuevo saldo: " + acc.getBalance());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Ingrese ID de la cuenta origen: ");
                    String idOrigen = sc.nextLine();
                    System.out.print("Ingrese ID de la cuenta destino: ");
                    String idDestino = sc.nextLine();
                    System.out.print("Ingrese monto a transferir: ");
                    int montoT = sc.nextInt();
                    sc.nextLine();

                    Account origen = null, destino = null;
                    for (Account acc : cuentas) {
                        if (acc.getID().equals(idOrigen)) origen = acc;
                        if (acc.getID().equals(idDestino)) destino = acc;
                    }
                    if (origen != null && destino != null) {
                        origen.transferTo(destino, montoT);
                        System.out.println("Transferencia realizada.");
                    } else {
                        System.out.println("Cuenta origen o destino no encontrada.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
