package rps.gui.controller;

// Java imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rps.bll.game.*;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author smsj
 */
public class GameViewController implements Initializable {

    IGameState gameState;
    GameManager ge;
    IPlayer bot;
    IPlayer human;

    @FXML
    private Label botLbl;
    @FXML
    private ImageView userMoveImg;
    @FXML
    private ImageView botMoveImg;
    @FXML
    private Label userName;
    @FXML
    private Label scoreBoardLbl;
    @FXML
    private Label resultLbl;

    Image imgR = new Image("/images/rock.jpg");
    Image imgP = new Image("/images/paper.jpg");
    Image imgS = new Image("/images/scissors.jpg");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String playerName = userName.getText();
        String botName = getRandomBotName();
        botLbl.setText(botName);
        human = new Player(playerName, PlayerType.Human);
        bot = new Player(botName, PlayerType.AI);

        ge = new GameManager(human, bot);
        gameState = new GameState(new ArrayList<>(), 0);

        scoreBoardLbl.setText("Rounds\n played!");
    }

    @FXML
    private void handleRockGame(ActionEvent event) {
        int roundNumber = gameState.getRoundNumber();
        if (roundNumber < 100) {
            ge.playRound(Move.Rock);
            userMoveImg.setImage(imgR);

            setImageBot();

            gameState.setRoundNumber(++roundNumber);
            scoreBoardLbl.setText(String.valueOf(roundNumber));
        } else showAlertHuman();
    }

    @FXML
    private void handlePaperGame(ActionEvent event) {
        int roundNumber = gameState.getRoundNumber();
        if (roundNumber < 100) {
            ge.playRound(Move.Paper);
            userMoveImg.setImage(imgP);

            setImageBot();

            gameState.setRoundNumber(++roundNumber);
            scoreBoardLbl.setText(String.valueOf(roundNumber));
        }
    }

    @FXML
    private void handleScissorGame(ActionEvent event) {
        int roundNumber = gameState.getRoundNumber();
        if (roundNumber < 100) {
            ge.playRound(Move.Scissor);
            userMoveImg.setImage(imgS);

            setImageBot();

            gameState.setRoundNumber(++roundNumber);
            scoreBoardLbl.setText(String.valueOf(roundNumber));
        }
    }

    public void setImageBot(){
        Move botMove = bot.doMove(gameState);
        if (botMove == Move.Rock) {
            botMoveImg.setImage(imgR);

        } else if (botMove == Move.Paper) {
            botMoveImg.setImage(imgP);

        } else if (botMove == Move.Scissor) {
            botMoveImg.setImage(imgS);
        }
    }

    /**
     * Famous robots...
     *
     * @return
     */
    public String getRandomBotName() {
        String[] botNames = new String[]{
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }

    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }

    public void showAlertHuman(){
        Alert alertH = new Alert(Alert.AlertType.INFORMATION);
        alertH.setTitle("You have won!!!");
        alertH.setContentText("You have won!!!");
        alertH.show();
    }
}
