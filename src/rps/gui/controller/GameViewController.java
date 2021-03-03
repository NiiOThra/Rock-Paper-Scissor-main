package rps.gui.controller;

// Java imports

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;
import rps.gui.ConsoleApp;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {


    @FXML
    private Label botLbl;
    @FXML
    private ImageView userMoveImg;
    @FXML
    private Label resultLbl;
    @FXML
    private Label userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String playerName = userName.getText();
        String botName = getRandomBotName();
        botLbl.setText(botName);
        IPlayer human = new Player(playerName, PlayerType.Human);
        IPlayer bot = new Player(botName, PlayerType.AI);

        GameManager ge = new GameManager(human, bot);
    }

    @FXML
    private void handleRockGame(ActionEvent event){
        Image img = new Image("/images/rock.jpg");
        userMoveImg.setImage(img);
        return Move.Rock;
    }

    @FXML
    private void handlePaperGame(ActionEvent event){
        Image img = new Image("/images/paper.jpg");
        userMoveImg.setImage(img);
        return Move.Paper;
    }

    @FXML
    private void handleScissorGame(ActionEvent event){
        Image img = new Image("/images/scissors.jpg");
        userMoveImg.setImage(img);
        return Move.Scissor;
    }

    /**
     * Famous robots...
     * @return
     */
    public String getRandomBotName() {
        String[] botNames = new String[] {
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
}
