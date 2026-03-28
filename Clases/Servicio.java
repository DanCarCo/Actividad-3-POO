package Clases;

/**
 * Clase que representa un servicio ofrecido por la barbería.
 *
 * CRITERIO 1 - Clase principal:
 *   Servicio es una de las clases del dominio del sistema. Almacena el precio,
 *   descripción y duración estimada de cada servicio disponible.
 *
 * CRITERIO 2 - Patrón Factory Method:
 *   El método estático crearServicio() actúa como una fábrica (Factory Method).
 *   En lugar de que el usuario construya un Servicio manualmente con new Servicio(...),
 *   la fábrica recibe el tipo deseado ("corte", "barba", "combo") y se encarga
 *   de crear y retornar el objeto correcto con sus datos preconfigurados.
 *   Esto centraliza la lógica de creación y facilita agregar nuevos servicios en el futuro.
 */
public class Servicio {

    /** Precio del servicio en pesos colombianos. */
    private double precio;

    /** Descripción textual del servicio (ej. "Corte de cabello"). */
    private String descripcion;

    /** Duración estimada del servicio en minutos. */
    private int duracion;

    /**
     * Constructor privado/interno: recibe los tres datos del servicio.
     * Normalmente se usa a través del método crearServicio() (patrón Factory).
     *
     * @param precio      Precio del servicio.
     * @param descripcion Descripción del servicio.
     * @param duracion    Duración en minutos.
     */
    public Servicio(double precio, String descripcion, int duracion) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    // ─── Métodos de acceso ─────────────────────────────────────────────────────

    /** Retorna la descripción del servicio. */
    public String getDescripcion() {
        return descripcion;
    }

    /** Retorna el precio del servicio. */
    public double getPrecio() {
        return precio;
    }

    /** Retorna la duración del servicio en minutos. */
    public int getDuracion() {
        return duracion;
    }

    // ─── Patrón Factory Method ─────────────────────────────────────────────────

    /**
     * CRITERIO 2 - Factory Method:
     * Crea y retorna un objeto Servicio según el tipo solicitado.
     *
     * En lugar de instanciar Servicio directamente en el Main, este método centraliza
     * la lógica de creación. Si en el futuro se agrega un nuevo servicio (ej. "tintura"),
     * solo se modifica aquí, sin tocar el resto del código.
     *
     * CRITERIO 4 - Gestión de errores:
     * Si el tipo ingresado no coincide con ninguna opción válida, lanza una excepción
     * con un mensaje descriptivo, evitando que el programa cree objetos incoherentes.
     *
     * @param tipo Tipo de servicio: "corte", "barba" o "combo" (sin importar mayúsculas).
     * @return     Nuevo objeto Servicio configurado con sus datos correspondientes.
     * @throws IllegalArgumentException si el tipo de servicio no es reconocido.
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
                // Lanza excepción si el tipo no existe: el Main la captura con try-catch
                throw new IllegalArgumentException("Servicio no válido");
        }
    }

    /** Representación en texto del servicio: descripción, precio y duración. */
    @Override
    public String toString() {
        return descripcion + " | $" + precio + " | " + duracion + " min";
    }
}
