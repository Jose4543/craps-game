package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        Dice dice1 = new Dice();
        dice1.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        Dice dice2 = new Dice();
        dice2.roll();

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );
    }

    @FXML
    private void onActionHelpButton() {
        javafx.scene.control.Alert alert =
                new javafx.scene.control.Alert(
                        javafx.scene.control.Alert.AlertType.INFORMATION);

        alert.setTitle("Craps Game - Ayuda");
        alert.setHeaderText("Instrucciones del juego");
        alert.setContentText(
                "Se juega con dos dados.\n\n" +
                        "En el primer lanzamiento, 7 u 11 significa ganar; " +
                        "2, 3 o 12 significa perder es un Craps.\n\n" +
                        "Si sale otro número, ese será tu punto. " +
                        "Debes repetirlo antes de sacar un 7 para ganar."
        );

        alert.getDialogPane().setPrefSize(480, 350);
        alert.showAndWait();
    }
}
