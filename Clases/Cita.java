package Clases;

public class Cita {

    private String fecha;
    private String hora;
    private String estado;
    private String comentario;
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