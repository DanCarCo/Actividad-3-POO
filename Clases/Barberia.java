package Clases;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase central que administra el sistema de la barbería.
 *
 * CRITERIO 2 - Patrón Singleton:
 *   Esta clase implementa el patrón Singleton, lo que garantiza que durante toda
 *   la ejecución del programa exista una sola instancia de Barberia.
 *   Esto es importante porque todos los profesionales y citas deben estar
 *   centralizados en un único lugar (como una base de datos simulada).
 *
 *   Cómo funciona el Singleton aquí:
 *     1. El constructor es privado → nadie puede hacer "new Barberia()" desde fuera.
 *     2. Se guarda la única instancia en el atributo estático "instancia".
 *     3. El método getInstancia() crea la instancia solo si aún no existe;
 *        de lo contrario, retorna la que ya estaba creada.
 *
 * CRITERIO 1 - Relaciones:
 *   La barbería tiene una lista de Profesionales (agregación) y una lista de
 *   Citas (composición), siendo el punto central de todas las operaciones.
 */
public class Barberia {

    /**
     * Atributo estático que almacena la única instancia de la barbería.
     * Es null al inicio y se inicializa la primera vez que se llama a getInstancia().
     */
    private static Barberia instancia;

    /** Lista de profesionales registrados en la barbería. */
    private List<Profesional> profesionales;

    /** Lista de todas las citas agendadas en la barbería. */
    private List<Cita> citas;

    /**
     * Constructor privado: evita que se creen instancias desde fuera de la clase.
     * Solo se ejecuta una vez, cuando getInstancia() lo llama por primera vez.
     */
    private Barberia() {
        profesionales = new ArrayList<>();
        citas = new ArrayList<>();
    }

    // ─── Patrón Singleton ──────────────────────────────────────────────────────

    /**
     * CRITERIO 2 - Singleton:
     * Punto de acceso global a la única instancia de la barbería.
     *
     * Si aún no se ha creado ninguna instancia, la crea y la guarda.
     * Si ya existe, simplemente la retorna. Esto asegura que siempre
     * se trabaje sobre el mismo conjunto de datos.
     *
     * @return La única instancia de Barberia en el sistema.
     */
    public static Barberia getInstancia() {
        if (instancia == null) {
            instancia = new Barberia();
        }
        return instancia;
    }

    // ─── Gestión de profesionales ──────────────────────────────────────────────

    /**
     * Agrega un profesional al sistema.
     * Se valida que el objeto no sea nulo para evitar datos corruptos.
     *
     * @param p Profesional a registrar.
     */
    public void agregarProfesional(Profesional p) {
        if (p != null) {
            profesionales.add(p);
        }
    }

    /**
     * Retorna la lista de profesionales disponibles.
     * Se entrega una copia para proteger la lista interna.
     *
     * @return Lista de profesionales registrados.
     */
    public List<Profesional> getProfesionales() {
        return new ArrayList<>(profesionales);
    }

    // ─── Gestión de citas ──────────────────────────────────────────────────────

    /**
     * Registra una nueva cita en el sistema central de la barbería.
     * Se valida que la cita no sea nula antes de agregarla.
     *
     * @param cita Cita a registrar.
     */
    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    /**
     * CRITERIO 3 - Programación funcional:
     * Muestra todas las citas del sistema usando forEach con referencia a método.
     * forEach recorre la lista y aplica System.out::println a cada elemento,
     * imprimiendo el resultado del toString() de cada Cita.
     */
    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    // ─── Operaciones con Streams (Programación funcional) ──────────────────────

    /**
     * CRITERIO 3 - Programación funcional (Stream + filter + collect):
     * Busca profesionales cuyo nombre coincida con el texto ingresado,
     * sin distinguir entre mayúsculas y minúsculas.
     *
     * Cómo funciona:
     *   1. profesionales.stream() → convierte la lista en un flujo de datos.
     *   2. .filter(...) → mantiene solo los profesionales cuyo nombre coincida.
     *   3. .collect(Collectors.toList()) → convierte el resultado en una lista.
     *
     * @param nombre Nombre a buscar entre los profesionales.
     * @return Lista de profesionales que coinciden con el nombre buscado.
     */
    public List<Profesional> filtrarPorNombre(String nombre) {
        return profesionales.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}
