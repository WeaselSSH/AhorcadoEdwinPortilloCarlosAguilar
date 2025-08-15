package ahorcadomain;

import java.util.Arrays;

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
        this.palabraSecreta = admin.obtenerAleatoria().toLowerCase();

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
