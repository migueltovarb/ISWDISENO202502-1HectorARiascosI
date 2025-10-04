package paqueteCuenta;

public class Aplicacion {
    public static void main(String[] args) {
        // Crear una cuenta solo con titular (cantidad empieza en 0)
        Cuenta cuenta1 = new Cuenta("Sebastián");
        System.out.println(cuenta1.toString());

        // Crear una cuenta con titular y cantidad inicial
        Cuenta cuenta2 = new Cuenta("Juan", 200);
        System.out.println(cuenta2.toString());

        // Probar ingresar
        cuenta1.ingresar(100);
        System.out.println("Después de ingresar 100 a cuenta1: " + cuenta1);

        cuenta2.ingresar(-50); // no debe hacer nada porque es negativo
        System.out.println("Después de intentar ingresar -50 a cuenta2: " + cuenta2);

        // Probar retirar
        cuenta1.retirar(30);
        System.out.println("Después de retirar 30 de cuenta1: " + cuenta1);

        cuenta2.retirar(300); // más de lo que tiene → debe quedar en 0
        System.out.println("Después de retirar 300 de cuenta2: " + cuenta2);
    }
}

