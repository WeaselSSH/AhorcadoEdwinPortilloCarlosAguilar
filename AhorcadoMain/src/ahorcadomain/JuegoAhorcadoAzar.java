/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcadomain;

/**
 *
 * @author adrianaguilar
 */
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class JuegoAhorcadoAzar extends JuegoAhorcadoBase {

    private final AdminPalabrasSecretas admin;

    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin) {
        this.admin = admin;
    }

    public JuegoAhorcadoAzar(AdminPalabrasSecretas admin, int limiteIntentos) {
        this.admin = admin;
        this.limiteIntentos = Math.max(1, limiteIntentos);
    }

    @Override
    public void inicializarPalabraSecreta() {
        if (admin == null || admin.size() == 0) {
            throw new IllegalStateException("No hay palabras disponibles para jugar.");
        }
        String palabra = admin.obtenerAleatoria();     
        this.palabraSecreta = palabra.toLowerCase();
        this.intentos = limiteIntentos;
        this.letrasUsadas.clear();
        this.palabraActual = new char[palabraSecreta.length()];
        Arrays.fill(this.palabraActual, '_');
       
    }

    @Override
    protected void actualizarPalabraActual(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraActual[i] = letra;
            }
        }
    }

    @Override
    protected boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    public boolean hasGanado() {
        return new String(palabraActual).equals(palabraSecreta);
    }

    @Override
    public void jugar() {
       
    }

    
    public String revelarPalabra() { return palabraSecreta; }
}

