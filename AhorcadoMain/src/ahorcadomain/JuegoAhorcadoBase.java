package ahorcadomain;

import java.util.ArrayList;

public abstract class JuegoAhorcadoBase implements JuegoAhorcado {

    protected String palabraSecreta;
    protected char[] palabraActual;
    protected int intentos;
    protected int limiteIntentos = 6;

    protected ArrayList<Character> letrasUsadas = new ArrayList<>();
    protected ArrayList<Character> letrasAdivinadas = new ArrayList<>();
    protected ArrayList<Character> letrasErradas = new ArrayList<>();
    protected final ArrayList<String> figuraAhorcado = new ArrayList<>();

    protected void initFiguraAhorcado() {
        figuraAhorcado.clear();
        figuraAhorcado.add("cabeza");
        figuraAhorcado.add("torso");
        figuraAhorcado.add("brazo_izq");
        figuraAhorcado.add("brazo_der");
        figuraAhorcado.add("pierna_izq");
        figuraAhorcado.add("pierna_der");
    }

    public ArrayList<String> getFiguraAhorcado() {
        return new ArrayList<>(figuraAhorcado);
    }

    public int getIntentosRestantes() {
        return intentos;
    }

    public int getLimiteIntentos() {
        return limiteIntentos;
    }

    public int getErrores() {
        return limiteIntentos - intentos;
    }

    public ArrayList<Character> getLetrasUsadas() {
        return new ArrayList<>(letrasUsadas);
    }

    public ArrayList<Character> getLetrasAdivinadas() {
        return new ArrayList<>(letrasAdivinadas);
    }

    public ArrayList<Character> getLetrasErradas() {
        return new ArrayList<>(letrasErradas);
    }

    protected String getPalabraSecreta() {
        return palabraSecreta;
    }

    public ResultadoIntento intentarConLetra(char letra) {
        char l = Character.toLowerCase(letra);

        if (!Character.isLetter(l)) {
            return ResultadoIntento.INVALIDA;
        }
        if (letrasUsadas.contains(l)) {
            return ResultadoIntento.REPETIDA;
        }

        letrasUsadas.add(l);

        if (verificarLetra(l)) {
            actualizarPalabraActual(l);
            if (!letrasAdivinadas.contains(l)) {
                letrasAdivinadas.add(l);
            }
            return ResultadoIntento.ACIERTO;
        } else {
            if (!letrasErradas.contains(l)) {
                letrasErradas.add(l);
            }
            intentos = Math.max(0, intentos - 1);
            return ResultadoIntento.ERROR;
        }
    }

    public boolean juegoTerminado() {
        return hasGanado() || intentos == 0;
    }

    public String getPalabraActual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabraActual.length; i++) {
            sb.append(palabraActual[i]);
            if (i < palabraActual.length - 1) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    protected abstract void actualizarPalabraActual(char letra);

    protected abstract boolean verificarLetra(char letra);

    public abstract boolean hasGanado();

    @Override
    public void jugar() {
        inicializarPalabraSecreta();
    }

}
