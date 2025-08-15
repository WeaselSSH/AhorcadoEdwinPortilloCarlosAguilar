package ahorcadomain;

import java.util.Arrays;

public class JuegoAhorcadoFijo extends JuegoAhorcadoBase {

    private final String palabraFija;

    public JuegoAhorcadoFijo(String palabraFija) {
        if (palabraFija == null || palabraFija.isBlank()) {
            throw new IllegalArgumentException("La palabra fija no puede ser vacía.");
        }
        this.palabraFija = palabraFija;
    }

    public JuegoAhorcadoFijo(String palabraFija, int limiteIntentos) {
        if (palabraFija == null || palabraFija.isBlank()) {
            throw new IllegalArgumentException("La palabra fija no puede ser vacía.");
        }
        this.palabraFija = palabraFija;
        this.limiteIntentos = Math.max(1, limiteIntentos);
    }

    @Override
    public void inicializarPalabraSecreta() {
        this.palabraSecreta = palabraFija.toLowerCase();

        this.intentos = limiteIntentos;
        this.letrasUsadas.clear();
        this.letrasAdivinadas.clear();
        this.letrasErradas.clear();

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

    public String revelarPalabra() {
        return palabraSecreta;
    }
}
