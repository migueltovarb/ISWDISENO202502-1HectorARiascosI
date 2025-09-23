package geometria;

public class Circle {
    // Atributo privado
    private double radius = 1.0;

    // Constructor por defecto
    public Circle() {
    }

    // Constructor con parámetro
    public Circle(double radius) {
        this.radius = radius;
    }

    // Getter
    public double getRadius() {
        return radius;
    }

    // Setter
    public void setRadius(double radius) {
        this.radius = radius;
    }

    // Método para calcular área
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Método para calcular circunferencia
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    // Método toString
    @Override
    public String toString() {
        return "Circle[radius=" + radius + "]";
    }
}
