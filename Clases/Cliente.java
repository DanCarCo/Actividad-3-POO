package Clases;

import java.util.ArrayList;
import java.util.List;


/*
 * PUNTO 1 - SUBCLASE: CLIENTE
 * Cliente hereda de Usuario usando la palabra clave "extends".
 * PUNTO 1 - RELACIÓN DE AGREGACIÓN
 * Un Cliente puede tener múltiples citas a lo largo del tiempo.
 * La lista "citas" representa esa relación: el cliente agrupa citas,
 * pero las citas también existen de forma independiente (están registradas
 * en la Barbería y en el Profesional).
 */
public class Cliente extends Usuario {

    private List<Cita> citas;

    public Cliente(String nombre, String email, String telefono) {
        super(nombre, email, telefono);
        this.citas = new ArrayList<>();
    }

    public void agendarCita(Cita cita) {
        if (cita != null) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    public void mostrarCitas() {
        citas.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre();
    }
}