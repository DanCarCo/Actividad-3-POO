package Clases;

import java.util.*;
import java.util.stream.Collectors;

public class Barberia {

    private static Barberia instancia;
    private List<Profesional> profesionales;
    private List<Cita> citas;

    private Barberia() {
        profesionales = new ArrayList<>();
        citas = new ArrayList<>();
    }

    public static Barberia getInstancia() {
        if (instancia == null) {
            instancia = new Barberia();
        }
        return instancia;
    }

    public void agregarProfesional(Profesional p) {
        if (p != null) {
            profesionales.add(p);
        }
    }

    public List<Profesional> getProfesionales() {
        return new ArrayList<>(profesionales);
    }

    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    // Programación funcional
    public List<Profesional> filtrarPorNombre(String nombre) {
        return profesionales.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}