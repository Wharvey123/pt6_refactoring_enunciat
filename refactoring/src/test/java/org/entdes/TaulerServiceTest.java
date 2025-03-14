package org.entdes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaulerServiceTest {

    private TaulerService taulerService;

    @BeforeEach
    void setUp() {
        taulerService = new TaulerService();
    }

    @Test
    void testComprovarGuanyadorX() {
        // X guanya amb una fila completa
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(1, 0); // O
        taulerService.tractarClicCasella(0, 1); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(0, 2); // X

        assertTrue(taulerService.isGameOver());
        assertEquals("X", taulerService.getGuanyador());
    }

    @Test
    void testComprovarGuanyadorO() {
        // O guanya amb una columna completa
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(1, 0); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(2, 2); // X
        taulerService.tractarClicCasella(2, 1); // O

        assertTrue(taulerService.isGameOver());
        assertEquals("O", taulerService.getGuanyador());
    }

    @Test
    void testComprovarEmpat() {
        // Partida que acaba en empat
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(0, 2); // X
        taulerService.tractarClicCasella(1, 1); // O
        taulerService.tractarClicCasella(1, 0); // X
        taulerService.tractarClicCasella(1, 2); // O
        taulerService.tractarClicCasella(2, 1); // X
        taulerService.tractarClicCasella(2, 0); // O
        taulerService.tractarClicCasella(2, 2); // X

        assertTrue(taulerService.isGameOver());
        assertEquals("", taulerService.getGuanyador());
    }

    @Test
    void testCasellaOcupada() {
        // Intentar marcar una casella ja ocupada
        taulerService.tractarClicCasella(0, 0); // X
        String resultat = taulerService.tractarClicCasella(0, 0); 
        // O intenta marcar la mateixa casella

        assertEquals("X", resultat);
        assertFalse(taulerService.isGameOver());
        assertEquals("", taulerService.getGuanyador());
    }

    @Test
    void testGuanyadorDiagonal() {
        // X guanya amb una diagonal completa
        taulerService.tractarClicCasella(0, 0); // X
        taulerService.tractarClicCasella(0, 1); // O
        taulerService.tractarClicCasella(1, 1); // X
        taulerService.tractarClicCasella(0, 2); // O
        taulerService.tractarClicCasella(2, 2); // X

        assertTrue(taulerService.isGameOver());
        assertEquals("X", taulerService.getGuanyador());
    }
}
