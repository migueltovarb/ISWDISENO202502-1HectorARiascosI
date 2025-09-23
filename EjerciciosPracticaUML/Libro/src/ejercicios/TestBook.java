package ejercicios;

public class TestBook {
    public static void main(String[] args) {
        Author a1 = new Author("Gabriel García Márquez", "ggm@gmail.com", 'm');
        Book b1 = new Book("Cien años de soledad", a1, 49.99, 5);

        System.out.println(b1); // llama automáticamente a toString()
        System.out.println("Autor del libro: " + b1.getAuthor().getName());
    }
}
