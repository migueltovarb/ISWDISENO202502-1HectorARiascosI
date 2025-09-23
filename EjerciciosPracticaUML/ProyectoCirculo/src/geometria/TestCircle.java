package geometria;

public class TestCircle {
    public static void main(String[] args) {
        // Crear círculos
        Circle c1 = new Circle();
        Circle c2 = new Circle(5.0);

        // Imprimir datos
        System.out.println(c1); // Circle[radius=1.0]
        System.out.println("Radio: " + c1.getRadius());
        System.out.println("Área: " + c1.getArea());
        System.out.println("Circunferencia: " + c1.getCircumference());

        System.out.println("\n" + c2); // Circle[radius=5.0]
        System.out.println("Radio: " + c2.getRadius());
        System.out.println("Área: " + c2.getArea());
        System.out.println("Circunferencia: " + c2.getCircumference());

        // Cambiar radio
        c2.setRadius(10.0);
        System.out.println("\nDespués de cambiar el radio:");
        System.out.println(c2);
        System.out.println("Área: " + c2.getArea());
        System.out.println("Circunferencia: " + c2.getCircumference());
    }
}
