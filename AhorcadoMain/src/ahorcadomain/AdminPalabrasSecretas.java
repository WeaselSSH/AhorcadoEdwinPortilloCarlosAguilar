/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadomain;

/**
 *
 * @author adrianaguilar
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdminPalabrasSecretas {
    private final ArrayList<String> palabras = new ArrayList<>();
    private final HashSet<String> indice = new HashSet<>();

    public AdminPalabrasSecretas() {
       
        addAll(Arrays.asList(
            "programacion","java","erick","teclado","pantalla",
            "internet","codigo","unitec","clase","objeto"
        ));
    }

   
    public boolean agregar(String palabra) {
        if (palabra == null) return false;
        String p = palabra.trim().toLowerCase();
        if (p.isEmpty()) return false;
        if (indice.add(p)) { palabras.add(p); return true; }
        return false;
    }

    
    public int addAll(List<String> nuevas) {
        int c = 0;
        for (String s : nuevas) if (agregar(s)) c++;
        return c;
    }

    
    public boolean eliminar(String palabra) {
        if (palabra == null) return false;
        String p = palabra.trim().toLowerCase();
        if (indice.remove(p)) { palabras.remove(p); return true; }
        return false;
    }

    
    public boolean eliminarIndex(int idx) {
        if (idx < 0 || idx >= palabras.size()) return false;
        String p = palabras.remove(idx);
        indice.remove(p);
        return true;
    }

    /** Limpia todas. */
    public void limpiar() {
        palabras.clear();
        indice.clear();
    }

 
    public void reemplazarTodo(List<String> nuevas) {
        limpiar();
        addAll(nuevas);
    }

    
    public ArrayList<String> listar() {
        return new ArrayList<>(palabras);
    }

   
    public int size() { return palabras.size(); }

    public String obtenerAleatoria() {
        if (palabras.isEmpty()) throw new IllegalStateException("No hay palabras disponibles.");
        int i = ThreadLocalRandom.current().nextInt(palabras.size());
        return palabras.get(i);
    }
}
