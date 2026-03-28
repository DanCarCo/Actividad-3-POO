import java.util.List;

public class Profesional {
    private String nombre;
    private List<String> horariosDisponibles;

    public Profesional(String nombre, List<String> horarios) {
        this.nombre = nombre;
        this.horariosDisponibles = horarios;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getHorariosDisponibles() {
        return horariosDisponibles;
    }
}