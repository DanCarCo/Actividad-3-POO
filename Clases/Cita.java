package Clases;

public class Cita extends Servicio {
    String fecha;
    String hora;
    String estado;
    String comentario;
    Cliente cliente;
    Profesional profesional;

    public Cita(double precio, String descripcion, int duracion, String fecha, String hora, String estado, String comentario, Cliente cliente) {
        super(precio, descripcion, duracion);
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.comentario = comentario;
        this.cliente = cliente;
    }
}
