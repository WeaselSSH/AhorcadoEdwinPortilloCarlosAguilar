package ahorcadomain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class AdminPalabrasSecretas {
    private final ArrayList<String> palabras = new ArrayList<>();
    private final HashSet<String> indice = new HashSet<>();

    public AdminPalabrasSecretas() {
       
        agregar("programacion");
        agregar("java");
        agregar("Erick");
        agregar("teclado");
        agregar("pantalla");
        agregar("internet");
        agregar("codigo");
        agregar("unitec");
        agregar("clase");
        agregar("objeto");
    }

   
    public boolean agregar(String palabra) {
        String p = palabra.toLowerCase();
        if (indice.add(p)) {
            palabras.add(p);
            return true;
        }
        return false;
    }

   
    public String obtenerAleatoria() {
        if (palabras.isEmpty()) {
            throw new IllegalStateException("No hay palabras disponibles.");
        }
        int i = ThreadLocalRandom.current().nextInt(palabras.size());
        return palabras.get(i);
    }

    public int size() { return palabras.size(); }

    public ArrayList<String> todas() {
        return new ArrayList<>(palabras);
    }
}
