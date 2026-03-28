package Clases;

/**
 * Clase base (superclase) que representa a cualquier persona registrada en el sistema.
 *
 * CRITERIO 1 - Clases y relaciones:
 *   Esta es la clase principal del sistema. De ella heredan Cliente y Profesional,
 *   lo que permite reutilizar los atributos comunes (nombre, email, teléfono)
 *   sin duplicar código en cada subclase.
 */
public class Usuario {

    // Atributos privados: solo se acceden desde fuera a través de los métodos get
    private String nombre;
    private String email;
    private String telefono;

    /**
     * Constructor: inicializa los datos básicos de cualquier usuario del sistema.
     *
     * @param nombre   Nombre completo de la persona.
     * @param email    Correo electrónico de contacto.
     * @param telefono Número telefónico de contacto.
     */
    public Usuario(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // ─── Métodos de acceso (getters) ───────────────────────────────────────────

    /** Retorna el nombre del usuario. */
    public String getNombre() {
        return nombre;
    }

    /** Retorna el correo electrónico del usuario. */
    public String getEmail() {
        return email;
    }

    /** Retorna el teléfono del usuario. */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Representación en texto del usuario.
     * Las subclases pueden sobrescribir este método para personalizar el mensaje.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre +
               " | Email: " + email +
               " | Teléfono: " + telefono;
    }
}
