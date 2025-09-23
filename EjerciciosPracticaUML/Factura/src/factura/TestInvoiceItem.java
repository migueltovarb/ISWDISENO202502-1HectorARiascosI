package factura;

public class TestInvoiceItem {
    public static void main(String[] args) {
        // Crear ítems de factura
        InvoiceItem item1 = new InvoiceItem("A101", "Laptop", 2, 3500.50);
        InvoiceItem item2 = new InvoiceItem("B202", "Mouse", 5, 120.75);

        // Imprimir información
        System.out.println(item1); 
        System.out.println("ID: " + item1.getID());
        System.out.println("Descripción: " + item1.getDesc());
        System.out.println("Cantidad: " + item1.getQty());
        System.out.println("Precio unitario: " + item1.getUnitPrice());
        System.out.println("Total: " + item1.getTotal());

        System.out.println("\n" + item2);
        System.out.println("Total: " + item2.getTotal());

        // Modificar cantidad y precio
        item2.setQty(10);
        item2.setUnitPrice(100.00);
        System.out.println("\nDespués de cambios:");
        System.out.println(item2);
        System.out.println("Total actualizado: " + item2.getTotal());
    }
}
