package veterinario;


public class Duenio {
    private String nombre;
    private String documento;
    private String telefono;

    public Duenio(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public String getDocumento() { return documento; }
    public String getTelefono() { return telefono; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDocumento(String documento) { this.documento = documento; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Dueño: " + nombre + " | Documento: " + documento + " | Teléfono: " + telefono;
    }
}
