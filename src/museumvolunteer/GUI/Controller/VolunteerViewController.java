package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.BLLFacade;
import museumvolunteer.BLL.ContainsSearch;
import museumvolunteer.BLL.SearchPattern;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
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

    @FXML
    private TextField noteHoursField;
    @FXML
    private DatePicker datePick;

    //private variables.
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private BLLFacade bllFacade;
    @FXML
    private TextField searchNameField;

    /**
     * Initializes the VolunteerViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dataBind();
//            List<Guild> allGuilds = guildTable.getItems();
//            List<String> allGuildNames = new ArrayList();
//            for (Guild g : allGuilds) {
//                String nameString = g.getNameAsString();
//                allGuildNames.add(nameString);
//            }
//            nameTable.setItems(volunteerModel.getNames());
//            
//            try {
//                Guild g = guildTable.getSelectionModel().getSelectedItem();
//                if (g != null) {
//                    int guildId = g.getIdValue();
//                    List<String> allVolunteers = bllFacade.getAllVolunteerNames(guildId);
//                    volunteerModel.setFilteredNames(allVolunteers);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(VolunteerViewController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            datePick.setValue(LocalDate.now());
            datePick.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * .getInstance for volunteerModel and guildsModel. Both are singleton. A new namesManager is created.
     * @throws IOException
     * @throws SQLException
     */
    public VolunteerViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
        bllFacade = new BLLFacade();
    }

    /**
     * Returns the volunteer to mainView.
     * @param event
     * @throws IOException 
     */
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

    /**
     * Populates guildsTable and namesTable with guilds and names.
     */
    private void dataBind() throws SQLException {

        guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getNameAsString()));

        guildTable.setItems(guildsModel.getGuilds());
        nameTable.setPlaceholder(new Label("Der er ikke nogen \nnavne at vise"));
        guildTable.setPlaceholder(new Label("Der er ikke nogen \nlaug at vise"));
    }

//    /**
//     * 
//     * @param volunteerModel
//     */
//    public void setModel(VolunteerModel volunteerModel) {
//        this.volunteerModel = volunteerModel;
//    }

    /**
     * Method for populating nameTable with volunteers matching same guild id.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (guildTable.getSelectionModel().getSelectedItem() != null) {
            if (event.isPrimaryButtonDown() == false) {
                int guildId = guildTable.getSelectionModel().getSelectedItem().getIdValue();
                volunteerModel.getNamesByGuildId(guildId);
                nameColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getNameAsString()));
                nameTable.setItems(volunteerModel.getAllVolunteers());
            }
        }
        searchNameField.clear();
  
    }

    /**
     * Method for adding hours to a specific volunteer, when both guild and name is selected.
     * @param event
     * @throws SQLException
     * @throws IOException 
     */
    @FXML
    private void insertHours(ActionEvent event) throws SQLException, IOException {

        if (datePick.getValue() != null && guildTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && nameTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && !noteHoursField.getText().isEmpty()) {

//        
//              
            LocalDateTime timeStamp = datePick.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            //datePicker timeStamp = datePick.getDayCellFactory().trim();
            int guildsId = guildTable.getSelectionModel().getSelectedItem().getIdValue();
            int nameId = nameTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
            //int nameId = Integer.parseInt(nameColumn.getText().trim());
            int hours = Integer.parseInt(noteHoursField.getText().trim());
            volunteerModel.addHours(new CheckIn(dateTime, guildsId, nameId, hours));
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText("Du har bidraget med " + noteHoursField.getText() + " timer");
            noteHoursField.clear();
            alert.showAndWait();
        } else if (datePick.getValue() == null || guildTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || nameTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || noteHoursField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbjede, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }

    /**
     * Method for searching through names in nameTable, when a guild is selected.
     * @param event
     * @throws SQLException 
     */
    @FXML
    void searchNameList(KeyEvent event) throws SQLException {
        String query = searchNameField.getText().trim();
        List<String> searchResult = null;
        SearchPattern searchStrategy;
        searchStrategy = new ContainsSearch(query);
        int guildId = guildTable.getSelectionModel().getSelectedItem().getIdValue();
        searchResult = bllFacade.search(searchStrategy, guildId);
        volunteerModel.setFilteredNames(searchResult);
        nameTable.setItems(volunteerModel.getNames().sorted());
        if(query.isEmpty())
        {
            int guildsId = guildTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.getNamesByGuildId(guildsId);
            nameColumn.setCellValueFactory(managerAdminCol -> managerAdminCol.getValue().getName());
            nameTable.setItems(volunteerModel.getAllVolunteers());
        }

    }
}
