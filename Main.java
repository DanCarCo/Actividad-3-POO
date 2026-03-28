import Clases.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.time.LocalDate;
import java.time.localDate;

public class Main {

    public static void main(String[] args) {

        try {
            Barberia barberia = Barberia.getInstancia();

            // 🔹 Crear profesionales (datos iniciales)
            Profesional p1 = new Profesional("Carlos", "carlos@gmail.com", "123", "Corte");
            Profesional p2 = new Profesional("Andres", "andres@gmail.com", "456", "Barba");

            barberia.agregarProfesional(p1);
            barberia.agregarProfesional(p2);

            // 🔹 DATOS DEL CLIENTE
            String nombreCliente = pedirTexto("Ingrese el nombre del cliente:");
            String emailCliente = pedirEmail("Ingrese el email del cliente:");
            String telefonoCliente = pedirTelefono("Ingrese el teléfono del cliente:");

            Cliente cliente = new Cliente(nombreCliente, emailCliente, telefonoCliente);

            // 🔹 SELECCIONAR PROFESIONAL (LISTA)
            Profesional profesional = seleccionarProfesional(barberia);

            // 🔹 TIPO DE SERVICIO
            String tipoServicio = pedirServicio("Ingrese el tipo de servicio (corte/barba/combo):");
            Servicio servicio = Servicio.crearServicio(tipoServicio);

            // 🔹 DATOS DE LA CITA
           
            String fecha = pedirFecha("Ingrese la fecha (YYYY-MM-DD):");
            String hora = pedirHora("Ingrese la hora (HH:MM):");
            String comentario = pedirTexto("Ingrese un comentario:");

            
            Cita cita = new Cita(fecha, hora, "Pendiente", comentario, cliente, profesional, servicio);

            // 🔹 AGENDAR
            barberia.agendarCita(cita);
            cliente.agendarCita(cita);
            profesional.agendarCita(cita);

            // 🔹 MOSTRAR RESULTADO
            JOptionPane.showMessageDialog(null,
                    "✅ Cita registrada correctamente:\n\n" + cita.toString(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "❌ Error: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(null,
                    "Programa finalizado.",
                    "Fin",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 🔥 ================= VALIDACIONES =================

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

    public static String pedirFecha(String mensaje) {
        String fecha;
        LocalDate hoy = LocalDate.now();

        do {
            fecha = JOptionPane.showInputDialog(null, mensaje);
            if (fecha == null) throw new RuntimeException("Operación cancelada");

            try {
                LocalDate fechaIngresada = LocalDate.parse(fecha);
                if (!fechaIngresada.isBefore(hoy)) break; // ✅ Fecha válida y no pasada
                JOptionPane.showMessageDialog(null, "❌ La fecha no puede ser anterior a hoy (" + hoy + ")");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "❌ Formato incorrecto. Usa: YYYY-MM-DD");
            }

        } while (true);

        return fecha;
    }

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

    // 🔥 ================= SELECCIÓN DE PROFESIONAL =================

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
