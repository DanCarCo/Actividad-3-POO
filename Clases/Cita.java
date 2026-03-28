package Clases;

public class Cita extends Servicio {
    public String fecha;
    public String hora;
    public String estado;
    public String comentario;
    public Cliente cliente;
    public Profesional profesional;

    public Cita(double precio, String descripcion, int duracion, String fecha, String hora, String estado, String comentario, Cliente cliente) {
        super(precio, descripcion, duracion);
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.comentario = comentario;
        this.cliente = cliente;
    }
}
