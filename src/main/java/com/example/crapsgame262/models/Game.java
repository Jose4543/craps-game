package com.example.crapsgame262.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo del juego Craps. Contiene SOLO la lógica (no usa JavaFX).
 *
 * Reglas:
 *  - Tiro de salida: 7 u 11 gana; 2, 3 o 12 ("craps") pierde;
 *    4, 5, 6, 8, 9, 10 establece el "punto".
 *  - Con punto establecido: sacar el punto gana; sacar 7 pierde;
 *    cualquier otro número sigue el juego.
 */
public class Game {

    /** Resultado de un lanzamiento. */
    public enum Result {
        WIN,        // La partida terminó y el jugador ganó
        LOSE,       // La partida terminó y el jugador perdió
        CONTINUE    // La partida sigue (se estableció el punto o se sigue lanzando)
    }

    private final Dice dice1;
    private final Dice dice2;

    private int roll;              // Suma del último lanzamiento (Tiro)
    private int point;             // Punto establecido (0 = no hay punto)
    private boolean inProgress;    // true si hay un punto establecido y la partida sigue
    private int gamesWon;
    private int gamesLost;

    // Estructura de datos: historial de tiros de la partida actual
    private final List<Integer> rollHistory;

    public Game() {
        dice1 = new Dice();
        dice2 = new Dice();
        rollHistory = new ArrayList<>();
    }

    /**
     * Lanza los dos dados, aplica las reglas y devuelve el resultado.
     */
    public Result play() {
        dice1.roll();
        dice2.roll();
        return evaluate(dice1.getValue() + dice2.getValue());
    }

    /**
     * Aplica las reglas de Craps a una suma de dados.
     * Es público para poder probar la lógica sin depender del azar.
     *
     * @param sum suma de los dos dados (2 a 12)
     */
    public Result evaluate(int sum) {
        if (!inProgress) {
            // Nueva partida: se limpian el punto y el historial anteriores
            point = 0;
            rollHistory.clear();
        }

        roll = sum;
        rollHistory.add(sum);

        if (!inProgress) {
            // ---- Tiro de salida ----
            if (sum == 7 || sum == 11) {
                return finish(true);
            }
            if (sum == 2 || sum == 3 || sum == 12) {
                return finish(false);
            }
            point = sum;
            inProgress = true;
            return Result.CONTINUE;
        }

        // ---- Ya hay punto establecido ----
        if (sum == point) {
            return finish(true);
        }
        if (sum == 7) {
            return finish(false);
        }
        return Result.CONTINUE;
    }

    private Result finish(boolean won) {
        inProgress = false;
        if (won) {
            gamesWon++;
            return Result.WIN;
        }
        gamesLost++;
        return Result.LOSE;
    }

    // ------------------------- Getters -------------------------

    public Dice getDice1() { return dice1; }

    public Dice getDice2() { return dice2; }

    /** Suma del último lanzamiento. */
    public int getRoll() { return roll; }

    /** Punto actual (0 si no hay). */
    public int getPoint() { return point; }

    public boolean isInProgress() { return inProgress; }

    public int getGamesWon() { return gamesWon; }

    public int getGamesLost() { return gamesLost; }

    /** Copia del historial de tiros de la partida actual. */
    public List<Integer> getRollHistory() { return new ArrayList<>(rollHistory); }
}