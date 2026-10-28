
package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;

    // Modelo del juego
    private final Game game = new Game();

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    // Estadísticas del juego
    @FXML
    private Label tiroLabel;

    @FXML
    private Label puntoLabel;

    @FXML
    private Label juegosGanadosLabel;

    @FXML
    private Label juegosPerdidosLabel;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {

        // Ejecutar la lógica del modelo
        game.play();

        // Obtener los dados del modelo
        Dice dice1 = game.getDice1();
        Dice dice2 = game.getDice2();

        // Actualizar las imágenes de los dados
        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );

        // Actualizar las estadísticas en la vista
        tiroLabel.setText(String.valueOf(game.getRoll()));
        puntoLabel.setText(
                game.getPoint() == 0
                        ? "-"
                        : String.valueOf(game.getPoint())
        );
        juegosGanadosLabel.setText(
                String.valueOf(game.getGamesWon())
        );
        juegosPerdidosLabel.setText(
                String.valueOf(game.getGamesLost())
        );
    }
    @FXML
    void onActionHelpButton() {
        // Aquí se implementará la ayuda del juego.
    }
}

