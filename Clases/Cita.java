package Clases;

/*
 * PUNTO 1 - CLASE PRINCIPAL: CITA
 * Una Cita representa el evento central del sistema: el momento en que un cliente
 * reserva tiempo con un profesional para recibir un servicio.
 *
 * PUNTO 1 - RELACIÓN DE COMPOSICIÓN
 * La Cita está compuesta por tres objetos que le dan sentido:
 *   - Cliente:      quién pide la cita
 *   - Profesional:  quién la atiende
 *   - Servicio:     qué se va a hacer (corte, barba o combo)
 * Sin cualquiera de estos tres elementos, la cita no tendría significado.
 * Esta relación se llama composición: la Cita "contiene" a esos objetos como parte
 * de su definición.
 *
 * Además guarda la fecha, la hora, el estado ("Pendiente", "Completada", etc.)
 * y un comentario adicional del cliente.
 */
public class Cita {

    private String fecha;
    private String hora;
    private String estado;
    private String comentario;

    // Referencias a los objetos relacionados (composición)
    private Cliente cliente;
    private Profesional profesional;
    private Servicio servicio;

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

    public Profesional getProfesional() {
        return profesional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    // Muestra un resumen completo de la cita con todos sus datos relevantes
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
