package banco;

public class TestAccount {
    public static void main(String[] args) {
        // Crear cuentas
        Account acc1 = new Account("A101", "Juan", 5000);
        Account acc2 = new Account("A102", "Ana", 3000);

        // Mostrar cuentas iniciales
        System.out.println(acc1);
        System.out.println(acc2);

        // Probar crédito
        acc1.credit(2000);
        System.out.println("\nDespués de acreditar 2000 a Juan:");
        System.out.println(acc1);

        // Probar débito
        acc1.debit(1000);
        System.out.println("\nDespués de debitar 1000 a Juan:");
        System.out.println(acc1);

        acc1.debit(7000); // excede saldo
        System.out.println(acc1);

        // Probar transferencia
        acc1.transferTo(acc2, 2000);
        System.out.println("\nDespués de transferir 2000 de Juan a Ana:");
        System.out.println(acc1);
        System.out.println(acc2);
    }
}
