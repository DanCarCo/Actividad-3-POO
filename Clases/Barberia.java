package Clases;

import java.util.*;
import java.util.stream.Collectors;

/*
 * PUNTO 1 - CLASE PRINCIPAL DEL SISTEMA
 * Barberia es la clase que administra toda la operación: guarda la lista de
 * profesionales y el historial de citas agendadas.
 *
 * PUNTO 2 - PATRÓN SINGLETON
 * Esta clase aplica el patrón Singleton, lo que significa que en todo el programa
 * solo puede existir UNA instancia de Barberia. Esto se logra así:
 *   - El constructor es privado, nadie puede hacer "new Barberia()" desde afuera.
 *   - La variable estática "instancia" guarda el único objeto creado.
 *   - El método getInstancia() devuelve siempre ese mismo objeto.
 * Esto es útil porque centraliza los datos y evita que haya varias "barberías"
 * con listas de citas diferentes y desincronizadas.
 */
public class Barberia {

    // Variable que guarda la única instancia de esta clase
    private static Barberia instancia;

    // Lista de profesionales registrados en la barbería
    private List<Profesional> profesionales;

    // Registro histórico de todas las citas agendadas
    private List<Cita> citas;

    // Constructor privado: impide que se creen objetos Barberia desde otras clases
    private Barberia() {
        profesionales = new ArrayList<>();
        citas = new ArrayList<>();
    }

    // Punto de acceso único a la barbería. Si aún no existe, la crea;
    // si ya existe, devuelve la misma que ya estaba creada.
    public static Barberia getInstancia() {
        if (instancia == null) {
            instancia = new Barberia();
        }
        return instancia;
    }

    // Agrega un profesional a la lista, solo si el objeto no es nulo
    public void agregarProfesional(Profesional p) {
        if (p != null) {
            profesionales.add(p);
        }
    }

    // Devuelve una copia de la lista de profesionales para evitar modificaciones externas
    public List<Profesional> getProfesionales() {
        return new ArrayList<>(profesionales);
    }

    // Registra una cita en el historial del sistema, solo si el objeto no es nulo
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    /*
     * PUNTO 3 - PROGRAMACIÓN FUNCIONAL
     * forEach() con referencia a método (System.out::println) es una forma
     * funcional de recorrer la lista e imprimir cada cita, equivalente a
     * un ciclo for pero más concisa y expresiva.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    /*
     * PUNTO 3 - PROGRAMACIÓN FUNCIONAL: STREAMS Y LAMBDAS
     * Este método usa la API de Streams para buscar profesionales por nombre.
     * - stream(): convierte la lista en un flujo de datos procesable.
     * - filter(): aplica una condición (lambda) para quedarse solo con los
     *   profesionales cuyo nombre coincida, ignorando mayúsculas/minúsculas.
     * - collect(): agrupa los resultados filtrados en una nueva lista.
     * Esto reemplaza un ciclo for con if anidado de forma más legible y declarativa.
     */
    public List<Profesional> filtrarPorNombre(String nombre) {
        return profesionales.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}
