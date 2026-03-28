package Clases;

import java.util.ArrayList;
import java.util.List;

/*
 * PUNTO 1 - SUBCLASE: CLIENTE
 * Cliente hereda de Usuario usando la palabra clave "extends".
 * Esto significa que Cliente ya tiene nombre, email y teléfono sin necesidad
 * de redeclararlos. Solo agrega lo que es específico de un cliente:
 * su historial de citas.
 *
 * PUNTO 1 - RELACIÓN DE AGREGACIÓN
 * Un Cliente puede tener múltiples citas a lo largo del tiempo.
 * La lista "citas" representa esa relación: el cliente agrupa citas,
 * pero las citas también existen de forma independiente (están registradas
 * en la Barbería y en el Profesional).
 */
public class Cliente extends Usuario {

    // Lista que guarda el historial de citas de este cliente
    private List<Cita> citas;

    // El constructor llama a super() para inicializar los atributos de Usuario
    public Cliente(String nombre, String email, String telefono) {
        super(nombre, email, telefono);
        this.citas = new ArrayList<>();
    }

    // Agrega una cita al historial del cliente, solo si el objeto no es nulo
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    // Devuelve una copia de la lista para proteger los datos internos
    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    /*
     * PUNTO 3 - PROGRAMACIÓN FUNCIONAL
     * forEach() con referencia a método recorre todas las citas del cliente
     * e imprime cada una. Es equivalente a un ciclo for tradicional pero
     * más expresivo y conciso.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre();
    }
}
