package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase que representa a un profesional (barbero/estilista) de la barbería.
 *
 * CRITERIO 1 - Herencia:
 *   Al igual que Cliente, Profesional hereda de Usuario. Ambas subclases
 *   comparten los datos básicos de la superclase y agregan sus propios atributos.
 *   Profesional añade la especialidad (ej. "Corte", "Barba").
 *
 * CRITERIO 1 - Composición:
 *   El profesional también lleva su propia lista de citas asignadas.
 */
public class Profesional extends Usuario {

    /** Especialidad del profesional dentro de la barbería (ej. "Corte", "Barba"). */
    private String especialidad;

    /**
     * Lista de citas asignadas a este profesional.
     * Relación de composición: las citas son parte integral del profesional.
     */
    private List<Cita> citas;

    /**
     * Constructor: crea un profesional con sus datos básicos heredados de Usuario
     * y su especialidad particular.
     *
     * @param nombre       Nombre del profesional.
     * @param email        Correo electrónico.
     * @param telefono     Teléfono de contacto.
     * @param especialidad Área de especialización dentro de la barbería.
     */
    public Profesional(String nombre, String email, String telefono, String especialidad) {
        // Llama al constructor de la superclase Usuario para inicializar los datos comunes
        super(nombre, email, telefono);
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    /** Retorna la especialidad del profesional. */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Agrega una cita al calendario del profesional.
     * Se valida que la cita no sea nula antes de registrarla.
     *
     * @param cita Objeto Cita a asignar al profesional.
     */
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    /**
     * Retorna una copia de la lista de citas del profesional.
     * La copia protege la lista interna contra modificaciones no deseadas.
     *
     * @return Lista de citas del profesional.
     */
    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    /**
     * CRITERIO 3 - Programación funcional:
     * Imprime las citas del profesional usando forEach con referencia a método.
     * Es equivalente a un ciclo for, pero más conciso gracias al estilo funcional.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    /** Representación en texto del profesional, incluyendo su nombre y especialidad. */
    @Override
    public String toString() {
        return "Profesional: " + getNombre() + " | Especialidad: " + especialidad;
    }
}
