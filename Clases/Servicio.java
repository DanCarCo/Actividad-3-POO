package Clases;

public class Servicio {

    private double precio;
    private String descripcion;
    private int duracion; 

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

  
    public static Servicio crearServicio(String tipo) {
        switch (tipo.toLowerCase()) {
            case "corte":
                return new Servicio(15000, "Corte de cabello", 30);
            case "barba":
                return new Servicio(10000, "Arreglo de barba", 20);
            case "combo":
                return new Servicio(20000, "Corte + Barba", 45);
            default:
                throw new IllegalArgumentException("Servicio no válido");
        }
    }

    @Override
    public String toString() {
        return descripcion + " | $" + precio + " | " + duracion + " min";
    }
}
