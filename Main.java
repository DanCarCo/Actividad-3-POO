import Clases.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.time.LocalDate;
import java.time.localDate;

/**
 * Clase principal del sistema de agendamiento de la Barbería.
 *
 * CRITERIO 4 - Gestión de errores:
 *   Todo el flujo principal está envuelto en un bloque try-catch-finally:
 *     - try:     ejecuta el proceso completo de agendamiento.
 *     - catch:   captura cualquier error inesperado y muestra un mensaje amigable.
 *     - finally: siempre informa al usuario que el programa terminó, sin importar
 *                si hubo error o no. Esto evita que el programa falle silenciosamente.
 *
 * CRITERIO 2 - Patrones de diseño usados:
 *   - Singleton:      Barberia.getInstancia() garantiza una sola instancia del sistema.
 *   - Factory Method: Servicio.crearServicio() crea el servicio según el tipo ingresado.
 */
public class Main {

    public static void main(String[] args) {

        // CRITERIO 4 - try-catch-finally: protege todo el proceso principal
        try {
            // Se obtiene la única instancia de la barbería (patrón Singleton)
            Barberia barberia = Barberia.getInstancia();

            // Se registran los profesionales disponibles en el sistema
            Profesional p1 = new Profesional("Carlos", "carlos@gmail.com", "123", "Corte");
            Profesional p2 = new Profesional("Andres", "andres@gmail.com", "456", "Barba");

            barberia.agregarProfesional(p1);
            barberia.agregarProfesional(p2);

            // ─── Datos del cliente ──────────────────────────────────────────────
            // Cada método pide el dato y valida que sea correcto antes de continuar

            // Nombre: no puede estar vacío
            String nombreCliente = pedirTexto("Ingrese el nombre del cliente:");

            // Email: debe contener "@" y "."
            String emailCliente = pedirEmail("Ingrese el email del cliente:");

            // Teléfono: solo dígitos numéricos
            String telefonoCliente = pedirTelefono("Ingrese el teléfono del cliente:");

            // Se crea el cliente con los datos validados
            Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);

            // ─── Selección del profesional ──────────────────────────────────────
            // El usuario ve la lista de profesionales y elige por número
            Profesional profesional = seleccionarProfesional(barberia);

            // ─── Tipo de servicio (patrón Factory Method) ──────────────────────
            // Se pide el tipo y la fábrica (Servicio.crearServicio) construye el objeto
            String tipoServicio = pedirServicio("Ingrese el tipo de servicio (corte/barba/combo):");
            Servicio servicio = Servicio.crearServicio(tipoServicio);

            // ─── Datos de la cita ───────────────────────────────────────────────
            // Fecha: formato YYYY-MM-DD y no puede ser anterior a hoy
            String fecha = pedirFecha("Ingrese la fecha (YYYY-MM-DD):");

            // Hora: formato HH:MM (de 00:00 a 23:59)
            String hora = pedirHora("Ingrese la hora (HH:MM):");

            // Comentario libre del cliente
            String comentario = pedirTexto("Ingrese un comentario:");

            // Se crea la cita relacionando cliente, profesional y servicio (agregación)
            Cita cita = new Cita(fecha, hora, "Pendiente", comentario, cliente, profesional, servicio);

            // ─── Registro de la cita ────────────────────────────────────────────
            // La cita se registra en los tres lugares: sistema central, cliente y profesional
            barberia.agendarCita(cita);
            cliente.agendarCita(cita);
            profesional.agendarCita(cita);

            // ─── Confirmación visual al usuario ─────────────────────────────────
            JOptionPane.showMessageDialog(null,
                    "✅ Cita registrada correctamente:\n\n" + cita.toString(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // CRITERIO 4 - Captura de errores:
            // Si ocurre cualquier error (datos inválidos, operación cancelada, etc.),
            // se muestra un mensaje descriptivo sin interrumpir el programa abruptamente.
            JOptionPane.showMessageDialog(null,
                    "❌ Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // CRITERIO 4 - finally:
            // Este bloque siempre se ejecuta, haya o no error.
            // Informa al usuario que el proceso terminó correctamente.
            JOptionPane.showMessageDialog(null,
                    "Programa finalizado.",
                    "Fin",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // MÉTODOS DE VALIDACIÓN
    // CRITERIO 4 - Cada método valida un tipo de dato específico con try-catch
    // y repite la solicitud hasta recibir un valor correcto.
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Solicita un texto no vacío al usuario mediante un cuadro de diálogo.
     * El ciclo do-while repite la solicitud si el campo queda vacío.
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Texto ingresado, garantizado no vacío.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirTexto(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(null, mensaje);

            // Si el usuario presiona "Cancelar", se detiene el programa con un error controlado
            if (dato == null) throw new RuntimeException("Operación cancelada");

            if (dato.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ El campo no puede estar vacío");
            }

        } while (dato.trim().isEmpty());

        return dato;
    }

    /**
     * Solicita un correo electrónico válido al usuario.
     * Valida que el texto contenga "@" y "." como mínimo.
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Correo electrónico válido ingresado por el usuario.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirEmail(String mensaje) {
        String email;
        do {
            email = JOptionPane.showInputDialog(null, mensaje);

            if (email == null) throw new RuntimeException("Operación cancelada");

            // Validación básica: el email debe tener @ y un punto
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(null, "❌ Email inválido (ej: usuario@gmail.com)");
            }

        } while (!email.contains("@") || !email.contains("."));

        return email;
    }

    /**
     * Solicita un número de teléfono que contenga solo dígitos numéricos.
     * Usa una expresión regular (\\d+) para verificar que todos los caracteres sean números.
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Teléfono numérico válido.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirTelefono(String mensaje) {
        String telefono;
        do {
            telefono = JOptionPane.showInputDialog(null, mensaje);

            if (telefono == null) throw new RuntimeException("Operación cancelada");

            // \\d+ significa: uno o más dígitos del 0 al 9, sin letras ni símbolos
            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "❌ Solo se permiten números");
            }

        } while (!telefono.matches("\\d+"));

        return telefono;
    }

    /**
     * Solicita el tipo de servicio y valida que sea una opción reconocida.
     * Las opciones válidas son: "corte", "barba" o "combo".
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Tipo de servicio válido en texto.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirServicio(String mensaje) {
        String tipo;
        do {
            tipo = JOptionPane.showInputDialog(null, mensaje);

            if (tipo == null) throw new RuntimeException("Operación cancelada");

            // Se acepta cualquier combinación de mayúsculas/minúsculas
            if (!tipo.equalsIgnoreCase("corte") &&
                !tipo.equalsIgnoreCase("barba") &&
                !tipo.equalsIgnoreCase("combo")) {

                JOptionPane.showMessageDialog(null,
                        "❌ Servicio inválido.\nOpciones: corte, barba, combo");
            }

        } while (!tipo.equalsIgnoreCase("corte") &&
                 !tipo.equalsIgnoreCase("barba") &&
                 !tipo.equalsIgnoreCase("combo"));

        return tipo;
    }

    /**
     * Solicita una fecha en formato YYYY-MM-DD y valida que:
     *   1. El formato sea correcto (usando LocalDate.parse que lanza excepción si no lo es).
     *   2. La fecha no sea anterior a la fecha actual (no se pueden agendar citas en el pasado).
     *
     * CRITERIO 4 - try-catch dentro de validación:
     *   El try-catch interno captura el error de formato sin detener el programa.
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Fecha válida como texto en formato YYYY-MM-DD.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirFecha(String mensaje) {
        String fecha;
        LocalDate hoy = LocalDate.now();

        do {
            fecha = JOptionPane.showInputDialog(null, mensaje);
            if (fecha == null) throw new RuntimeException("Operación cancelada");

            try {
                // LocalDate.parse valida automáticamente el formato YYYY-MM-DD
                LocalDate fechaIngresada = LocalDate.parse(fecha);

                // Se verifica que la fecha no sea pasada
                if (!fechaIngresada.isBefore(hoy)) break; // Fecha válida → salir del ciclo

                JOptionPane.showMessageDialog(null, "❌ La fecha no puede ser anterior a hoy (" + hoy + ")");
            } catch (Exception e) {
                // Si el formato es incorrecto, LocalDate.parse lanza una excepción
                JOptionPane.showMessageDialog(null, "❌ Formato incorrecto. Usa: YYYY-MM-DD");
            }

        } while (true);

        return fecha;
    }

    /**
     * Solicita una hora en formato HH:MM (de 00:00 a 23:59).
     * Usa una expresión regular para garantizar que el formato sea estrictamente correcto.
     *
     * La expresión ^([01]\\d|2[0-3]):([0-5]\\d)$ significa:
     *   - [01]\\d : horas del 00 al 19
     *   - 2[0-3]  : horas del 20 al 23
     *   - [0-5]\\d: minutos del 00 al 59
     *
     * @param mensaje Instrucción que se muestra al usuario.
     * @return Hora válida en formato HH:MM.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static String pedirHora(String mensaje) {
        String hora;
        do {
            hora = JOptionPane.showInputDialog(null, mensaje);

            if (hora == null) throw new RuntimeException("Operación cancelada");

            if (!hora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
                JOptionPane.showMessageDialog(null, "❌ Formato correcto: HH:MM");
            }

        } while (!hora.matches("^([01]\\d|2[0-3]):([0-5]\\d)$"));

        return hora;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // SELECCIÓN DE PROFESIONAL
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Muestra la lista de profesionales disponibles y solicita al usuario que
     * seleccione uno ingresando su número correspondiente.
     *
     * CRITERIO 4 - Gestión de errores:
     *   Se usa try-catch para capturar el caso en que el usuario ingrese letras
     *   en lugar de un número, evitando que el programa falle con NumberFormatException.
     *   El ciclo se repite hasta obtener una opción válida dentro del rango.
     *
     * @param barberia Instancia del sistema (Singleton) que contiene los profesionales.
     * @return El objeto Profesional seleccionado por el usuario.
     * @throws RuntimeException si el usuario cancela el diálogo.
     */
    public static Profesional seleccionarProfesional(Barberia barberia) {

        List<Profesional> lista = barberia.getProfesionales();

        // Se construye el mensaje con la lista numerada de profesionales
        String mensaje = "Seleccione un profesional:\n\n";
        for (int i = 0; i < lista.size(); i++) {
            mensaje += (i + 1) + ". " + lista.get(i).toString() + "\n";
        }

        int opcion = -1;

        do {
            String input = JOptionPane.showInputDialog(null, mensaje);

            if (input == null) throw new RuntimeException("Operación cancelada");

            try {
                // Se intenta convertir el texto a número entero
                opcion = Integer.parseInt(input);

                // Se verifica que el número esté dentro del rango válido
                if (opcion < 1 || opcion > lista.size()) {
                    JOptionPane.showMessageDialog(null, "❌ Opción fuera de rango");
                }

            } catch (NumberFormatException e) {
                // Si el usuario escribió letras, se captura el error y se pide de nuevo
                JOptionPane.showMessageDialog(null, "❌ Debe ingresar un número");
            }

        } while (opcion < 1 || opcion > lista.size());

        // Se retorna el profesional en la posición elegida (ajustando el índice base 0)
        return lista.get(opcion - 1);
    }
}
