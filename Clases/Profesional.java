package Clases;

import java.util.ArrayList;
import java.util.List;

/*
 * PUNTO 1 - SUBCLASE: PROFESIONAL
 * Profesional también hereda de Usuario, igual que Cliente.
 * Comparten nombre, email y teléfono (definidos en Usuario), pero Profesional
 * agrega su especialidad ("Corte", "Barba", etc.) y su propia agenda de citas.
 *
 * PUNTO 1 - RELACIÓN DE AGREGACIÓN
 * Un Profesional puede tener múltiples citas asignadas.
 * La lista "citas" representa su agenda de trabajo. Al igual que con el cliente,
 * las citas existen también en la Barbería y en el Cliente, porque la misma cita
 * es referenciada desde varios objetos a la vez.
 */
public class Profesional extends Usuario {

    // Tipo de trabajo en el que se especializa este profesional
    private String especialidad;

    // Agenda con todas las citas que tiene asignadas este profesional
    private List<Cita> citas;

    // El constructor llama a super() para heredar los datos de Usuario
    // y agrega el parámetro propio de Profesional: la especialidad
    public Profesional(String nombre, String email, String telefono, String especialidad) {
        super(nombre, email, telefono);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // Agrega una cita a la agenda del profesional, solo si el objeto no es nulo
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    // Devuelve una copia de la agenda para proteger los datos internos
    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    /*
     * PUNTO 3 - PROGRAMACIÓN FUNCIONAL
     * forEach() con referencia a método recorre todas las citas del profesional
     * e imprime cada una sin necesidad de un ciclo for explícito.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Profesional: " + getNombre() + " | Especialidad: " + especialidad;
    }
}
