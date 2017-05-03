/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.ContainsSearch;
import museumvolunteer.BLL.NamesManager;
import museumvolunteer.BLL.SearchPattern;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class VolunteerViewController implements Initializable {

    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, String> guildColumn;
    @FXML
    private TableView<Volunteer> nameTable;
    @FXML
    private TableColumn<Volunteer, String> nameColumn;
    @FXML
    private AnchorPane VolunteerScreen;

    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    @FXML
    private TextField searchnameField;
    @FXML
    private TextField noteHoursField;
    private NamesManager namesManager;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataBind();
        List<Guild> allGuilds = guildTable.getItems();
        List<String> allGuildNames = new ArrayList();
        for (Guild g : allGuilds) {
            String nameString = g.getName();
            allGuildNames.add(nameString);
        }
        nameTable.setItems(volunteerModel.getNames());
        
        try {
            List<String> allVolunteers = namesManager.getAllVolunteerNames();
            volunteerModel.setFilteredNames(allVolunteers);
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public VolunteerViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
        namesManager = new NamesManager();
    }


    @FXML
    private void backVolunteer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        stage = (Stage) VolunteerScreen.getScene().getWindow();
        stage.close();
    }

    private void dataBind() {
        guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
        guildTable.setItems(guildsModel.getGuilds());
        nameTable.setPlaceholder(new Label("Der er ikke \nnogen navne \nat vise"));
        guildTable.setPlaceholder(new Label("Der er ikke \nnogen laug \nat vise"));
    }

    public void setModel(VolunteerModel volunteerModel) {
        this.volunteerModel = volunteerModel;
    }

    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (event.isPrimaryButtonDown() == false) {
            int guildId = guildTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setNamesByGuildId(guildId);
            nameColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
            nameTable.setItems(volunteerModel.getAllVolunteers());
        }
    }

    @FXML
    private void insertHours(ActionEvent event) {
    }

    @FXML
    void searchNameList(KeyEvent event) throws SQLException{
        String query = searchnameField.getText().trim();
        List<String> searchResult = null;
        SearchPattern searchStrategy;
        searchStrategy = new ContainsSearch(query);
        searchResult = namesManager.search(searchStrategy);
        volunteerModel.setFilteredNames(searchResult);
        nameTable.setItems(volunteerModel.getNames());
    }
}
