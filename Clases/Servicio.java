package Clases;
public class Servicio {
    double precio;
    String descripcion;
    int duracion; 

    public Servicio(double precio, String descripcion, int duracion) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }
    
    public int getDuracion() {
        return duracion;    
    }

}
