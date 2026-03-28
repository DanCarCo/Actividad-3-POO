package Clases;

import java.util.ArrayList;
import java.util.List;

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