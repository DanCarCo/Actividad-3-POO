package Clases;

/*
 * PUNTO 1 - CLASE PRINCIPAL: SERVICIO
 * Representa un servicio ofrecido por la barbería. Cada servicio tiene
 * un precio, una descripción y un tiempo estimado de duración en minutos.
 *
 * PUNTO 2 - PATRÓN FACTORY METHOD
 * El método estático crearServicio() actúa como una fábrica de objetos Servicio.
 * En lugar de que el código principal tenga que saber cuánto cuesta cada servicio
 * y cuánto dura, simplemente llama a crearServicio("corte") y recibe el objeto
 * ya configurado correctamente. Esto centraliza la lógica de creación y facilita
 * agregar nuevos tipos de servicio en el futuro sin tocar el resto del programa.
 */
public class Servicio {

    private double precio;
    private String descripcion;
    private int duracion; // duración en minutos

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

    /*
     * PUNTO 2 - FACTORY METHOD
     * Este método recibe el tipo de servicio como texto y construye el objeto
     * Servicio con los valores correctos según el tipo:
     *   - "corte"  → $15.000, 30 minutos
     *   - "barba"  → $10.000, 20 minutos
     *   - "combo"  → $20.000, 45 minutos (corte + barba)
     * Si el tipo no coincide con ninguno de los anteriores, lanza una excepción
     * que será capturada por el bloque try-catch en Main.
     */
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
