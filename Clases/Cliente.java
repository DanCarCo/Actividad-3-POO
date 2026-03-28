package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Subclase que representa a un cliente de la barbería.
 *
 * CRITERIO 1 - Herencia:
 *   Cliente hereda de Usuario, por lo que obtiene automáticamente los atributos
 *   nombre, email y teléfono sin necesidad de redefinirlos.
 *
 * CRITERIO 1 - Composición:
 *   Un Cliente está compuesto por una lista de Citas. Esto es composición porque
 *   las citas pertenecen al cliente y se gestionan dentro de su ciclo de vida.
 */
public class Cliente extends Usuario {

    /**
     * Lista de citas que tiene agendadas este cliente.
     * Relación de composición: el cliente "contiene" sus propias citas.
     */
    private List<Cita> citas;

    /**
     * Constructor: crea un nuevo cliente llamando al constructor de la superclase
     * e inicializando su lista de citas vacía.
     *
     * @param nombre   Nombre del cliente.
     * @param email    Correo electrónico del cliente.
     * @param telefono Teléfono del cliente.
     */
    public Cliente(String nombre, String email, String telefono) {
        // Se reutiliza el constructor de Usuario mediante super()
        super(nombre, email, telefono);
        this.citas = new ArrayList<>();
    }

    /**
     * Agrega una cita a la lista del cliente.
     * Se valida que la cita no sea nula antes de agregarla.
     *
     * @param cita Objeto Cita a registrar.
     */
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    /**
     * Retorna una copia de la lista de citas del cliente.
     * Se entrega una copia para proteger la lista interna de modificaciones externas.
     *
     * @return Lista de citas del cliente.
     */
    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    /**
     * CRITERIO 3 - Programación funcional:
     * Muestra todas las citas del cliente usando una expresión lambda (forEach + referencia a método).
     * Equivale a recorrer la lista e imprimir cada elemento con System.out.println.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    /** Representación en texto del cliente, mostrando solo su nombre. */
    @Override
    public String toString() {
        return "Cliente: " + getNombre();
    }
}
