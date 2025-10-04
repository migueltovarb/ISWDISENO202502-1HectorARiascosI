package paqueteCuenta;

public class Cuenta {
    // Atributos
    private String titular;
    private double cantidad;

    // Constructor con titular obligatorio y cantidad opcional (por defecto 0)
    public Cuenta(String titular) {
        this.titular = titular;
        this.cantidad = 0;
    }

    // Constructor con titular y cantidad
    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        this.cantidad = cantidad;
    }

    // Métodos getter y setter
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // Método toString para mostrar la información de la cuenta
    @Override
    public String toString() {
        return "Cuenta{" +
                "titular='" + titular + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    // Método especial: ingresar
    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
        }
    }

    // Método especial: retirar
    public void retirar(double cantidad) {
        if (this.cantidad - cantidad < 0) {
            this.cantidad = 0;
        } else {
            this.cantidad -= cantidad;
        }
    }
}

