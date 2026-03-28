package Clases;

/**
 * Clase que representa una cita agendada en la barbería.
 *
 * CRITERIO 1 - Clase principal y relaciones (Asociación / Agregación):
 *   Una Cita agrupa y relaciona tres clases del sistema:
 *     - Cliente:      quién solicita el servicio.
 *     - Profesional:  quién realiza el servicio.
 *     - Servicio:     qué trabajo se va a hacer.
 *
 *   Esta relación es de agregación: la Cita referencia a objetos que existen
 *   de forma independiente (el cliente y el profesional no desaparecen si se
 *   cancela la cita). Así se modela una asociación real entre las partes del sistema.
 */
public class Cita {

    /** Fecha de la cita en formato YYYY-MM-DD. */
    private String fecha;

    /** Hora de la cita en formato HH:MM. */
    private String hora;

    /** Estado actual de la cita (ej. "Pendiente", "Completada", "Cancelada"). */
    private String estado;

    /** Comentario adicional del cliente sobre la cita (ej. preferencias de corte). */
    private String comentario;

    // ─── Relaciones de agregación con otras clases ─────────────────────────────

    /** Cliente que solicita la cita. */
    private Cliente cliente;

    /** Profesional asignado para atender la cita. */
    private Profesional profesional;

    /** Servicio que se realizará en la cita. */
    private Servicio servicio;

    /**
     * Constructor: crea una cita con todos los datos necesarios.
     * Se reciben los tres objetos relacionados (cliente, profesional, servicio)
     * para establecer la agregación entre las clases.
     *
     * @param fecha        Fecha de la cita (YYYY-MM-DD).
     * @param hora         Hora de la cita (HH:MM).
     * @param estado       Estado inicial de la cita.
     * @param comentario   Observaciones del cliente.
     * @param cliente      Cliente que agenda la cita.
     * @param profesional  Profesional asignado.
     * @param servicio     Servicio a realizar.
     */
    public Cita(String fecha, String hora, String estado, String comentario,
                Cliente cliente, Profesional profesional, Servicio servicio) {

        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.comentario = comentario;
        this.cliente = cliente;
        this.profesional = profesional;
        this.servicio = servicio;
    }

    // ─── Métodos de acceso ─────────────────────────────────────────────────────

    /** Retorna el profesional asignado a la cita. */
    public Profesional getProfesional() {
        return profesional;
    }

    /** Retorna el cliente que agendó la cita. */
    public Cliente getCliente() {
        return cliente;
    }

    /** Retorna el servicio asociado a la cita. */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Representación completa de la cita en texto.
     * Muestra todos los datos relevantes: fecha, hora, cliente, profesional,
     * servicio, estado y comentario.
     */
    @Override
    public String toString() {
        return "Cita: " + fecha + " " + hora +
               " | Cliente: " + cliente.getNombre() +
               " | Profesional: " + profesional.getNombre() +
               " | Servicio: " + servicio.getDescripcion() +
               " | Estado: " + estado +
               " | Comentario: " + comentario;
    }
}
