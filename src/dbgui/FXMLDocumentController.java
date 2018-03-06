/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Sebas
 */
public class FXMLDocumentController implements Initializable {

    private final DBTest db = new DBTest();

    @FXML
    private TextField NumberOfParticipants;
    @FXML
    private ListView<String> CoachesAndTeamsList;
    @FXML
    private Label PlayerAndTeams;
    @FXML
    private ListView<String> TeamsWithWinsList;
    @FXML
    private ListView<String> TeamsList;
    @FXML
    private Label PlayersOnTeam;
    @FXML
    private ListView<String> Tournaments;
    @FXML
    private GridPane LoginScreen;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private CheckBox RememberMe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoginScreen.setVisible(false);
        /*Insert into Starter Class
        Stage mainStage = new Stage();
        mainStage.setMinHeight(480);
        mainStage.setMinWidth(720);
        mainStage.setHeight(480);
        mainStage.setWidth(720);
        mainStage.setResizable(false);
        mainStage.show();

        Image icon = new Image(getClass().getResourceAsStream("Images/icon.png"));
        mainStage.setTitle("Counter Strike Database Management");

        mainStage.getIcons().add(icon);
         */
    }

    @FXML
    private void numberEntered(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String amount = NumberOfParticipants.getText();
            NumberOfParticipants.clear();
            System.out.println(amount);
        }
    }

    @FXML
    private void SearchClicked(MouseEvent event) {
        String amount = NumberOfParticipants.getText();
        NumberOfParticipants.clear();
        System.out.println(amount);
    }

    @FXML
    private void Login(ActionEvent event) {
        System.out.println(Username.getText() + Password.getText());
        if (Username.getText().equals("Admin") && Password.getText().equals("Password")) {
            System.out.println("Right");
            LoginScreen.setDisable(true);
            LoginScreen.setOpacity(0);

        }
    }

    @FXML
    private void fetchCoachedAndPlayers(Event event) {
        //gets list of names and teams of all coaches
        if (CoachesAndTeamsList.getChildrenUnmodifiable().isEmpty()) {

            CoachesAndTeamsList.setItems(FXCollections.observableList(db.executeQuery("SELECT * FROM coaches")));
        }

    }

    @FXML
    private void fetchTeamsAndPlayers(Event event) {
        //gets list of names of players and coaches of teams who have won a tournament
        if (TeamsWithWinsList.getChildrenUnmodifiable().isEmpty()) {

            TeamsWithWinsList.setItems(FXCollections.observableList(db.executeQuery("SELECT * FROM coaches")));
        }

    }

    @FXML
    private void fetchTournaments(Event event) {
        //get list of tournaments with at least as many participating teams as the user inputs

    }

    @FXML
    private void fetchPlayersAndCoaches(Event event) {
        //gets list of teams and number of players on it.

    }

}
