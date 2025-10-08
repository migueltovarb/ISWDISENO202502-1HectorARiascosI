package veterinario;

public class ControlVeterinario {
    private String fecha;
    private TipoControl tipo;
    private String observaciones;

    public ControlVeterinario(String fecha, TipoControl tipo, String observaciones) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.observaciones = observaciones;
    }

    public String getFecha() { return fecha; }
    public TipoControl getTipo() { return tipo; }
    public String getObservaciones() { return observaciones; }

    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setTipo(TipoControl tipo) { this.tipo = tipo; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + " | Tipo: " + tipo + " | Observaciones: " + observaciones;
    }
}
