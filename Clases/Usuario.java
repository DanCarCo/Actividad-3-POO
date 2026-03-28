package Clases;

/*
 * PUNTO 1 - CLASE PRINCIPAL: USUARIO
 * Usuario es la clase base del sistema. Contiene los atributos comunes a
 * cualquier persona que interactúe con la barbería: nombre, email y teléfono.
 *
 * Esta clase es el punto de partida de una jerarquía de herencia:
 *   Usuario
 *   ├── Cliente   (quien agenda la cita)
 *   └── Profesional (quien atiende la cita)
 *
 * Al colocar los atributos compartidos aquí, evitamos repetir código en cada
 * subclase. Tanto Cliente como Profesional heredan nombre, email y teléfono
 * automáticamente y solo añaden lo que les es propio.
 */
public class Usuario {

    private String nombre;
    private String email;
    private String telefono;

    public Usuario(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               " | Email: " + email +
               " | Teléfono: " + telefono;
    }
}
