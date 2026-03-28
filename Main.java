import Clases.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.time.LocalDate;
import java.time.localDate;

/*
 * PUNTO 4 - GESTIÓN DE ERRORES Y VALIDACIONES
 * El método main está envuelto en un bloque try-catch-finally para garantizar
 * que el programa nunca se detenga abruptamente, sin importar qué error ocurra.
 * Si algo falla (datos inválidos, operación cancelada, etc.), el mensaje de error
 * se muestra de forma amigable al usuario y el programa cierra de forma controlada.
 */
public class Main {

    public static void main(String[] args) {

        try {
            /*
             * PUNTO 2 - PATRÓN SINGLETON
             * En lugar de crear un objeto Barberia con "new Barberia()", se usa
             * getInstancia(). Esto garantiza que durante toda la ejecución del programa
             * solo exista UNA barbería, que centraliza todos los profesionales y citas.
             */
            Barberia barberia = Barberia.getInstancia();

            // Se registran dos profesionales con sus datos iniciales en el sistema
            Profesional p1 = new Profesional("Carlos", "carlos@gmail.com", "123", "Corte");
            Profesional p2 = new Profesional("Andres", "andres@gmail.com", "456", "Barba");

            barberia.agregarProfesional(p1);
            barberia.agregarProfesional(p2);

            /*
             * PUNTO 4 - VALIDACIONES DE ENTRADA
             * Cada dato que ingresa el usuario pasa por un método especializado de validación.
             * Si el usuario deja el campo vacío, ingresa un email sin "@", un teléfono con letras,
             * o cancela el diálogo, el programa muestra un mensaje de error claro y vuelve a pedir
             * el dato sin cerrarse.
             */
            String nombreCliente = pedirTexto("Ingrese el nombre del cliente:");
            String emailCliente = pedirEmail("Ingrese el email del cliente:");
            String telefonoCliente = pedirTelefono("Ingrese el teléfono del cliente:");

            /*
             * PUNTO 1 - CLASES Y SUBCLASES
             * Cliente hereda de Usuario (ver Cliente.java y Usuario.java).
             * Aquí se crea una instancia de Cliente pasando los datos validados.
             */
            Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);

            // El usuario elige a cuál profesional desea asignar su cita
            Profesional profesional = seleccionarProfesional(barberia);

            /*
             * PUNTO 2 - PATRÓN FACTORY METHOD
             * En lugar de construir el Servicio directamente con "new Servicio(...)",
             * se llama al método crearServicio() que actúa como una fábrica:
             * recibe el tipo ("corte", "barba" o "combo") y devuelve el objeto
             * Servicio ya configurado con su precio y duración correspondientes.
             */
            String tipoServicio = pedirServicio("Ingrese el tipo de servicio (corte/barba/combo):");
            Servicio servicio = Servicio.crearServicio(tipoServicio);

            // Se solicita la fecha y hora de la cita con validación de formato
            String fecha = pedirFecha("Ingrese la fecha (YYYY-MM-DD):");
            String hora = pedirHora("Ingrese la hora (HH:MM):");
            String comentario = pedirTexto("Ingrese un comentario:");

            /*
             * PUNTO 1 - RELACIONES ENTRE CLASES (COMPOSICIÓN)
             * La Cita agrupa al cliente, al profesional y al servicio en un solo objeto.
             * Esto representa una relación de composición: la cita no tiene sentido
             * si no existe quién la pide, quién la atiende y qué se va a hacer.
             */
            Cita cita = new Cita(fecha, hora, "Pendiente", comentario, cliente, profesional, servicio);

            // La cita queda registrada en el sistema central de la barbería,
            // en el historial del cliente y en la agenda del profesional
            barberia.agendarCita(cita);
            cliente.agendarCita(cita);
            profesional.agendarCita(cita);

            // Se muestra un resumen completo de la cita registrada
            JOptionPane.showMessageDialog(null,
                    "✅ Cita registrada correctamente:\n\n" + cita.toString(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            /*
             * PUNTO 4 - MANEJO DE EXCEPCIONES
             * Cualquier error inesperado (o lanzado intencionalmente por las validaciones)
             * es capturado aquí. El mensaje de la excepción se muestra al usuario
             * de forma clara, sin números de línea ni tecnicismos.
             */
            JOptionPane.showMessageDialog(null,
                    "❌ Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            /*
             * PUNTO 4 - BLOQUE FINALLY
             * Este bloque se ejecuta siempre, haya ocurrido un error o no.
             * Garantiza que el programa cierre de manera controlada y el usuario
             * siempre recibe un mensaje de cierre.
             */
            JOptionPane.showMessageDialog(null,
                    "Programa finalizado.",
                    "Fin",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // ============================================================
    // PUNTO 4 - MÉTODOS DE VALIDACIÓN
    // Cada método valida un tipo de dato diferente. Si el dato no
    // cumple las condiciones, muestra un error y vuelve a pedirlo.
    // ============================================================

    // Valida que el campo de texto no esté vacío
    public static String pedirTexto(String mensaje) {
        String dato;
        do {
            dato = JOptionPane.showInputDialog(null, mensaje);

            if (dato == null) throw new RuntimeException("Operación cancelada");

            if (dato.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ El campo no puede estar vacío");
            }

        } while (dato.trim().isEmpty());

        return dato;
    }

    // Valida que el email tenga formato básico: debe contener "@" y "."
    public static String pedirEmail(String mensaje) {
        String email;
        do {
            email = JOptionPane.showInputDialog(null, mensaje);

            if (email == null) throw new RuntimeException("Operación cancelada");

            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(null, "❌ Email inválido (ej: usuario@gmail.com)");
            }

        } while (!email.contains("@") || !email.contains("."));

        return email;
    }

    // Valida que el teléfono solo contenga dígitos, sin letras ni espacios
    public static String pedirTelefono(String mensaje) {
        String telefono;
        do {
            telefono = JOptionPane.showInputDialog(null, mensaje);

            if (telefono == null) throw new RuntimeException("Operación cancelada");

            if (!telefono.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "❌ Solo se permiten números");
            }

        } while (!telefono.matches("\\d+"));

        return telefono;
    }

    // Valida que el servicio sea exactamente uno de los tres tipos disponibles
    public static String pedirServicio(String mensaje) {
        String tipo;
        do {
            tipo = JOptionPane.showInputDialog(null, mensaje);

            if (tipo == null) throw new RuntimeException("Operación cancelada");

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

    // Valida el formato de la fecha (YYYY-MM-DD) y que no sea una fecha del pasado
    public static String pedirFecha(String mensaje) {
        String fecha;
        LocalDate hoy = LocalDate.now();

        do {
            fecha = JOptionPane.showInputDialog(null, mensaje);
            if (fecha == null) throw new RuntimeException("Operación cancelada");

            try {
                LocalDate fechaIngresada = LocalDate.parse(fecha);
                if (!fechaIngresada.isBefore(hoy)) break; // Fecha válida: hoy o futura
                JOptionPane.showMessageDialog(null, "❌ La fecha no puede ser anterior a hoy (" + hoy + ")");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "❌ Formato incorrecto. Usa: YYYY-MM-DD");
            }

        } while (true);

        return fecha;
    }

    // Valida que la hora tenga formato de 24 horas (HH:MM), por ejemplo 09:30 o 17:00
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

    // ============================================================
    // PUNTO 3 - PROGRAMACIÓN FUNCIONAL (uso indirecto)
    // Este método construye el listado de profesionales usando un ciclo
    // simple para mostrar las opciones. La lógica funcional con streams
    // se encuentra dentro de la clase Barberia (filtrarPorNombre).
    // ============================================================

    // Muestra la lista de profesionales disponibles y valida que el usuario
    // elija un número dentro del rango correcto
    public static Profesional seleccionarProfesional(Barberia barberia) {

        List<Profesional> lista = barberia.getProfesionales();
        String mensaje = "Seleccione un profesional:\n\n";

        for (int i = 0; i < lista.size(); i++) {
            mensaje += (i + 1) + ". " + lista.get(i).toString() + "\n";
        }

        int opcion = -1;

        do {
            String input = JOptionPane.showInputDialog(null, mensaje);

            if (input == null) throw new RuntimeException("Operación cancelada");

            try {
                opcion = Integer.parseInt(input);

                if (opcion < 1 || opcion > lista.size()) {
                    JOptionPane.showMessageDialog(null, "❌ Opción fuera de rango");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "❌ Debe ingresar un número");
            }

        } while (opcion < 1 || opcion > lista.size());

        return lista.get(opcion - 1);
    }
}
