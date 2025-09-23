package clases;

public class TestAuthor {
    public static void main(String[] args) {
        // Crear un autor
        Author a1 = new Author("Ana Torres", "ana@gmail.com", 'f');

        // Mostrar datos
        System.out.println(a1.getName());
        System.out.println(a1.getEmail());
        System.out.println(a1.getGender());

        // Cambiar email y mostrar toString
        a1.setEmail("ana.torres@yahoo.com");
        System.out.println(a1.toString());
    }
}
