package Clases;

import java.util.ArrayList;
import java.util.List;

public class Profesional extends Usuario {

    private String especialidad;
    private List<Cita> citas;

    public Profesional(String nombre, String email, String telefono, String especialidad) {
        super(nombre, email, telefono);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Profesional: " + getNombre() + " | Especialidad: " + especialidad;
    }
}