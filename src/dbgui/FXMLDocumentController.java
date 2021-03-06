/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private GridPane LoginScreen;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private CheckBox RememberMe;
    @FXML
    private ListView<?> TournamentsList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void numberEntered(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String amount = NumberOfParticipants.getText();
            NumberOfParticipants.clear();
            fetchTournaments(Integer.parseInt(amount));
        }
    }

    @FXML
    private void SearchClicked(MouseEvent event) {
        String amount = NumberOfParticipants.getText();
        NumberOfParticipants.clear();
        fetchTournaments(Integer.parseInt(amount));
    }

    @FXML
    private void Login(ActionEvent event) {
        if (Username.getText().equals("Admin") && Password.getText().equals("Password")) {
            LoginScreen.setDisable(true);
            LoginScreen.setOpacity(0);
        } else {
            System.out.println("Wrong username or password.");
            System.out.println("Try Username: Admin, Password: Password");
        }
    }

    @FXML
    private void fetchCoachedAndPlayers(Event event) {
        //gets list of names and teams of all coaches
        List listeditems = 
                db.executeQuery("SELECT DISTINCT teams, people FROM coaches "
                        + "ORDER BY teams");
        //listeditems = newGroupedList(listeditems);
        //Collections.sort(listeditems);
        CoachesAndTeamsList.setItems(FXCollections.observableList(listeditems));
    }

    @FXML
    private void fetchTeamsAndPlayers(Event event) {
        //gets list of teams and number of players on it.

        List listeditems = 
                db.executeQuery(
                "SELECT teams, COUNT(teams) FROM playson "
                + "GROUP BY teams "
                + "ORDER BY teams");
        TeamsList.setItems(FXCollections.observableList(listeditems));
    }

    private void fetchTournaments(int num) {
        //get list of tournaments with at least as many participating teams as the user inputs

        List listeditems = 
                db.executeQuery(
                "SELECT COUNT(teams), tournaments  FROM participatesin "
                + "GROUP BY tournaments "
                + "HAVING COUNT(teams) >= " + num
                + " ORDER BY COUNT(teams) DESC");
        TournamentsList.setItems(FXCollections.observableList(listeditems));
    }

    @FXML
    private void fetchPlayersAndCoaches(Event event) {
        //gets list of names of players and coaches of teams who have won a tournament

        List listeditems = 
                db.executeQuery(
                "SELECT teams, people FROM coaches WHERE teams "
                + "IN (SELECT winner FROM tournaments) UNION "
                + "SELECT teams, people FROM playson WHERE teams "
                + "IN (SELECT winner FROM tournaments) "
                + "ORDER BY teams");
        TeamsWithWinsList.setItems(FXCollections.observableList(listeditems));
    }


}
