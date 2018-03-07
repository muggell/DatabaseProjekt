/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbgui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        // TODO
        // LoginScreen.setVisible(false);
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
            fetchTournaments(Integer.parseInt(amount));
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
        if (Username.getText().equals("Admin") && Password.getText().equals("Password")) {
            LoginScreen.setDisable(true);
            LoginScreen.setOpacity(0);

        }
    }

    @FXML
    private void fetchCoachedAndPlayers(Event event) {
        //gets list of names and teams of all coaches
        if (CoachesAndTeamsList.getChildrenUnmodifiable().isEmpty()) {
            System.out.println("Players and coaches:");
            List listeditems = db.executeQuery("SELECT * FROM playson UNION SELECT * FROM coaches");
            listeditems = newGroupedList(listeditems);
            Collections.sort(listeditems);
            CoachesAndTeamsList.setItems(FXCollections.observableList(listeditems));
        }

    }

    @FXML
    private void fetchTeamsAndPlayers(Event event) {
        //gets list of teams and number of players on it.
        if (TeamsList.getChildrenUnmodifiable().isEmpty()) {

            List listeditems = db.executeQuery(
                    "SELECT playson.teams, COUNT(playson.teams) FROM playson"
                    + " JOIN teams ON playson.teams = teams.name "
                    + "GROUP BY playson.teams");

            //listeditems = newGroupedList(listeditems);
            //Collections.sort(listeditems);
            TeamsList.setItems(FXCollections.observableList(listeditems));
        }
    }

    private void fetchTournaments(int num) {
        //get list of tournaments with at least as many participating teams as the user inputs
        if (TournamentsList.getChildrenUnmodifiable().isEmpty()) {

            List listeditems = db.executeQuery(
                    "SELECT tournaments FROM participatesin "
                    + "GROUP BY tournaments "
                    + "HAVING COUNT(teams) >= " + num);
            TournamentsList.setItems(FXCollections.observableList(listeditems));
        }
    }

    @FXML
    private void fetchPlayersAndCoaches(Event event) {
        //gets list of names of players and coaches of teams who have won a tournament
        //if (TeamsWithWinsList.getChildrenUnmodifiable().isEmpty()) {
        if (TeamsWithWinsList.getChildrenUnmodifiable().isEmpty()) {

            List listeditems = db.executeQuery(
                    "SELECT * FROM coaches WHERE coaches.teams "
                    + "IN (SELECT winner FROM tournaments) UNION "
                    + "SELECT * FROM playson WHERE playson.teams "
                    + "IN (SELECT winner FROM tournaments)");
            List listed = newGroupedList(listeditems);
            Collections.sort(listed);
            TeamsWithWinsList.setItems(FXCollections.observableList(listed));
        }
        //TeamsWithWinsList.setItems(FXCollections.observableList(db.executeQuery("SELECT winner FROM tournaments")));

        //}
    }

    public List<String> newGroupedList(List<String> list) {
        int i = 0;
        List<String> val = new ArrayList();
        for (String line : list) {
            i++;
            String[] word = line.split("\t");
            val.add(word[1] + "\t" + word[0]);
        }
        return val;
    }

}
