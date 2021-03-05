package rps.gui.controller;

// Java imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ImageView botImg;
    @FXML
    private Label resultLbl;
    @FXML
    private Label scoreBoard;

    Image imgR = new Image("/images/rock.jpg");
    Image imgP = new Image("/images/paper.jpg");
    Image imgS = new Image("/images/scissors.jpg");
    int humanScore = 0;
    int botScore = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String botName = getRandomBotName();
        botLbl.setText(botName);
        human = new Player("human", PlayerType.Human);
        bot = new Player(botName, PlayerType.AI);

        ge = new GameManager(human, bot);
        gameState = new GameState(new ArrayList<>(), 0);

        botImg.setImage(new Image("/images/bot.png"));
    }

    @FXML
    private void handleRockGame(ActionEvent event) {
        Result rockGame = ge.playRound(Move.Rock);
        userMoveImg.setImage(imgR);

        getScoreBoard(rockGame);
        getResultAsString(rockGame);

        scoreBoard.setText((humanScore + "   " + botScore));
    }

    @FXML
    private void handlePaperGame(ActionEvent event) {
        Result paperGame = ge.playRound(Move.Paper);
        userMoveImg.setImage(imgP);

        getScoreBoard(paperGame);
        getResultAsString(paperGame);

        scoreBoard.setText((humanScore + "   " + botScore));
    }

    @FXML
    private void handleScissorGame(ActionEvent event) {
        Result scissorGame = ge.playRound(Move.Scissor);
        userMoveImg.setImage(imgS);

        getScoreBoard(scissorGame);
        getResultAsString(scissorGame);

        scoreBoard.setText((humanScore + "   " + botScore));
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

    private void getScoreBoard(Result humanPlay){
        if (humanPlay.getWinnerPlayer().getPlayerName().equals("human") && !humanPlay.getType().name().equals("Tie")){
            humanScore++;
        } else if (humanPlay.getType() == ResultType.Tie) {
            humanScore++;
            botScore++;
        } else botScore++;
    }

    private void getResultAsString(Result result){
        if (result.getType() == ResultType.Win) {
            resultLbl.setText("Round#" + result.getRoundNumber() + ": \n" +
                    result.getWinnerPlayer().getPlayerName() + " played " + result.getWinnerMove().name() + "\n and won over \n" +
                    result.getLoserPlayer().getPlayerName() + " that played " + result.getLoserMove().name());
        } else resultLbl.setText("Round#" + result.getRoundNumber() + ": \n it's a " + result.getType().name() + "!\n " +
                result.getWinnerPlayer().getPlayerName() + " and " + result.getLoserPlayer().getPlayerName() + " \nboth played " + result.getWinnerMove());
    }
}
