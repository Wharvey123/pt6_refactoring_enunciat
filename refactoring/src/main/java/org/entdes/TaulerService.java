package org.entdes;

public class TaulerService {

    private static final int TAMANY_TAULER = 3; // Tamany del tauler
    private static final String CASELLA_BUIDA = ""; // Casella buida
    private static final String JUGADOR_X = "X"; // Jugador X
    private static final String JUGADOR_O = "O"; // Jugador O

    private String[][] caselles; // Matriu de caselles del tauler
    private boolean esTornX = true; // Indica si és el torn del jugador X
    private boolean gameOver = false; // Indica si el joc ha acabat
    private String guanyador = CASELLA_BUIDA; // Guanyador del joc

    public TaulerService() {
        this.caselles = new String[TAMANY_TAULER][TAMANY_TAULER]; // Inicialitza la matriu de caselles
        inicialitzarTauler(); // Inicialitza el tauler
    }

    // Inicialitza el tauler amb caselles buides
    private void inicialitzarTauler() {
        for (int i = 0; i < TAMANY_TAULER; i++) {
            for (int j = 0; j < TAMANY_TAULER; j++) {
                caselles[i][j] = CASELLA_BUIDA;
            }
        }
    }

    // Tracta el clic d'una casella
    public String tractarClicCasella(int fila, int columna) {
        if (!gameOver && esCasellaBuida(fila, columna)) {
            marcarCasella(fila, columna);
            comprovarGuanyador();
            comprovarEmpat();
        }
        return caselles[fila][columna];
    }

    // Comprova si la casella està buida
    private boolean esCasellaBuida(int fila, int columna) {
        return caselles[fila][columna].isEmpty();
    }

    // Marca la casella amb el símbol del jugador corresponent
    private void marcarCasella(int fila, int columna) {
        caselles[fila][columna] = esTornX ? JUGADOR_X : JUGADOR_O;
        esTornX = !esTornX;
    }

    // Comprova si hi ha un guanyador
    private void comprovarGuanyador() {
        if (comprovarFiles() || comprovarColumnes() || comprovarDiagonals()) {
            gameOver = true;
        }
    }

    // Comprova les files per veure si hi ha un guanyador
    private boolean comprovarFiles() {
        for (int i = 0; i < TAMANY_TAULER; i++) {
            if (comprovarTresIguals(caselles[i][0], caselles[i][1], caselles[i][2])) {
                guanyador = caselles[i][0];
                return true;
            }
        }
        return false;
    }

    // Comprova les columnes per veure si hi ha un guanyador
    private boolean comprovarColumnes() {
        for (int i = 0; i < TAMANY_TAULER; i++) {
            if (comprovarTresIguals(caselles[0][i], caselles[1][i], caselles[2][i])) {
                guanyador = caselles[0][i];
                return true;
            }
        }
        return false;
    }

    // Comprova les diagonals per veure si hi ha un guanyador
    private boolean comprovarDiagonals() {
        if (comprovarTresIguals(caselles[0][0], caselles[1][1], caselles[2][2])) {
            guanyador = caselles[0][0];
            return true;
        }
        if (comprovarTresIguals(caselles[2][0], caselles[1][1], caselles[0][2])) {
            guanyador = caselles[2][0];
            return true;
        }
        return false;
    }

    // Comprova si tres caselles tenen el mateix valor
    private boolean comprovarTresIguals(String a, String b, String c) {
        return !a.isEmpty() && a.equals(b) && b.equals(c);
    }

    // Comprova si hi ha un empat
    private void comprovarEmpat() {
        if (!gameOver && esTaulerPle()) {
            gameOver = true;
        }
    }

    // Comprova si el tauler està ple
    private boolean esTaulerPle() {
        for (int i = 0; i < TAMANY_TAULER; i++) {
            for (int j = 0; j < TAMANY_TAULER; j++) {
                if (caselles[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Retorna el guanyador del joc
    public String getGuanyador() {
        return guanyador;
    }

    // Retorna si el joc ha acabat
    public boolean isGameOver() {
        return gameOver;
    }
}