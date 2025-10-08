package veterinario;

import java.util.ArrayList;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private Duenio duenio;
    private ArrayList<ControlVeterinario> controles;

    public Mascota(String nombre, String especie, int edad, Duenio dueño) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.duenio = dueño;
        this.controles = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }
    public Duenio getDuenio() { return duenio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setDuenio(Duenio duenio) { this.duenio = duenio; }

    public void agregarControl(ControlVeterinario control) {
        controles.add(control);
    }

    public ArrayList<ControlVeterinario> getControles() {
        return controles;
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + "\n" + duenio.toString();
    }
}

