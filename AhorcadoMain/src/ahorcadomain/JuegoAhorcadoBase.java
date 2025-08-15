/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadomain;
import java.util.ArrayList;

/**
 *
 * @author adrianaguilar
 */

public abstract class JuegoAhorcadoBase implements JuegoAhorcado {

    protected String palabraSecreta;
    protected char[] palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;
    protected ArrayList<Character> letrasUsadas = new ArrayList<>();
    protected ArrayList<String> figuraAhorcado = new ArrayList<>();

    public int getIntentosRestantes() { 
        return intentos; 
    }

    public int getLimiteIntentos() { 
        return limiteIntentos; 
    }

    public ArrayList<Character> getLetrasUsadas() { 
        return letrasUsadas; 
    }

    protected String getPalabraSecreta() { 
        return palabraSecreta; 
    }

    public boolean intentarConLetra(char letra) {
        char l = Character.toLowerCase(letra);

        if (!Character.isLetter(l)) return false;
        if (letrasUsadas.contains(l)) return false; 

        letrasUsadas.add(l);

        boolean acierto = verificarLetra(l);
        if (acierto) {
            actualizarPalabraActual(l);
        } else {
            intentos = Math.max(0, intentos - 1);
        }
        return acierto;
    }

    public boolean juegoTerminado() {
        return hasGanado() || intentos == 0;
    }

    public String getPalabraActual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabraActual.length; i++) {
            sb.append(palabraActual[i]);
            if (i < palabraActual.length - 1) sb.append(' ');
        }
        return sb.toString();
    }

    public String getFigura() {
        int errores = limiteIntentos - intentos;
        int idx = Math.min(errores, figuraAhorcado.size() - 1);
        return figuraAhorcado.isEmpty() ? "" : figuraAhorcado.get(idx);
    }

    protected abstract void actualizarPalabraActual(char letra);
    protected abstract boolean verificarLetra(char letra);
    public abstract boolean hasGanado();
}

