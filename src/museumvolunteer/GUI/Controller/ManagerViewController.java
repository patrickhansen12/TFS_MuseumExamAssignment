package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
public class ManagerViewController implements Initializable {

    @FXML
    private TableView<Guild> guildManagerTable;
    @FXML
    private TableColumn<Guild, String> guildManagerColumn;
    @FXML
    private TableView<Volunteer> nameManagerTable;
    @FXML
    private TableColumn<Volunteer, String> nameManagerColumn;
    @FXML
    private AnchorPane ManagerScreen;
    @FXML
    private TableView<CheckIn> hoursManagerTable;
    @FXML
    private TableColumn<CheckIn, Integer> hoursManagerColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtFieldHours;
    @FXML
    private TableColumn<CheckIn, Timestamp> dateManagerColumn;
    @FXML
    private Button volunteerInfo;

    //private variables.
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;
    private CheckIn checkIn;
    private BLLFacade bllFacade;
    @FXML
    private TextField searchNameField;

    /**
     * Initializes the ManagerViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        backgroundColor();
        datePicker.setValue(LocalDate.now());
        dataBind();
       List<Guild> allGuilds = guildManagerTable.getItems();
        List<String> allGuildNames = new ArrayList<String>();
        for (Guild g : allGuilds) {
            String nameString = g.getName();
            allGuildNames.add(nameString);
        }
        nameManagerTable.setItems(volunteerModel.getNames());

        try {
            Guild g = guildManagerTable.getSelectionModel().getSelectedItem();
            if (g != null) {
                int guildId = g.getId();
                List<String> allVolunteers = bllFacade.getAllVolunteerNames(guildId);
                volunteerModel.setFilteredNames(allVolunteers);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (nameManagerTable != null) {

        }
        if (txtFieldHours != null) {

        }
    }

    /**
     * Gets instances of singleton models volunteerModel and guildsModel.
     *
     * @throws IOException
     * @throws SQLException
     */
    public ManagerViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
        bllFacade = new BLLFacade();
    }

    /**
     * Opens the AddVolunteer view.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void addVolunteersButton(ActionEvent event) throws IOException {
        nameManagerTable.getColumns().get(0).setVisible(false);
        hoursManagerTable.getColumns().get(0).setVisible(false);
        hoursManagerTable.getColumns().get(1).setVisible(false);
        hoursManagerTable.getSelectionModel().clearSelection();
        nameManagerTable.getSelectionModel().clearSelection();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method for deleting a selected volunteer both in the observable list
     * Volunteer, but also in database table Names.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void deleteVolunteersButton(ActionEvent event) throws SQLException {
        if (nameManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette dem.");
            alert.showAndWait();
        }

        if (nameManagerTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog with Custom Actions");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på du vil slette " + nameManagerTable.getSelectionModel().getSelectedItem().getName() + "?");

            ButtonType buttonTypeThis = new ButtonType("Godkend");
//            ButtonType buttonTypeAll = new ButtonType("Alle laug");
            ButtonType buttonTypeCancel = new ButtonType("Anuller", ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeThis, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeThis) {
                Volunteer selectedItem = nameManagerTable.getSelectionModel().getSelectedItem();
                volunteer = selectedItem;
                volunteerModel.deleteVolunteer(volunteer);
                nameManagerTable.getItems().remove(selectedItem);
                nameManagerTable.getSelectionModel().clearSelection();
//            } else if (result.get() == buttonTypeAll) {
//                Volunteer selectedItem = nameManagerTable.getSelectionModel().getSelectedItem();
//                volunteer = selectedItem;
//                volunteerModel.deleteVolunteer(volunteer);
//                nameManagerTable.getItems().remove(selectedItem);
//                nameManagerTable.getSelectionModel().clearSelection();

                // ... user chose CANCEL or closed the dialog
            }
        }
    }

    /**
     * Returns the manager to MainView.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void returnManager(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        stage = (Stage) ManagerScreen.getScene().getWindow();
        stage.close();
    }

    private void dataBind() {

        guildManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
        guildManagerTable.setItems(guildsModel.getGuilds());
        guildManagerTable.setPlaceholder(new Label("Der er ikke \nnogen laug \nat vise"));
        nameManagerTable.setPlaceholder(new Label("Der er ikke \nnogen navne \nat vise"));
        hoursManagerTable.setPlaceholder(new Label("Der er ikke \nnogen timer \nat vise"));
    }

//    /**
//     * 
//     * @param volunteerModel
//     */
//    public void setModel(VolunteerModel volunteerModel) {
//        this.volunteerModel = volunteerModel;
//    }
    /**
     * Method for selecting all volunteers matching the guild that was clicked.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException, IOException {
        if (guildManagerTable.getSelectionModel().getSelectedItem() != null) {

            if (event.isPrimaryButtonDown() == false) {
                nameManagerTable.getColumns().get(0).setVisible(true);
                int guildId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
                volunteerModel.setNamesByGuildId(guildId);

                nameManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
                nameManagerTable.setItems(volunteerModel.getAllVolunteers().sorted());

                int guildsId = -1;
                int hourId = -1;

                volunteerModel.setCheckInsByNameIdGuildsId(guildsId, hourId);
                hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
                dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
                hoursManagerTable.setItems(volunteerModel.getAllCheckIns());

            }
            searchNameField.clear();

        }
    }

    /**
     * Adds hours to the selected volunteer, both in the observable list
     * CheckIn, but also in the database table Hours.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void addHoursButton(ActionEvent event) throws SQLException, IOException {
        if (datePicker.getValue() != null && guildManagerTable.getSelectionModel().getSelectedItem() != null && nameManagerTable.getSelectionModel().getSelectedItem() != null && !txtFieldHours.getText().isEmpty()) {
            LocalDateTime timeStamp = datePicker.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            //datePicker timeStamp = datePick.getDayCellFactory().trim();
            int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
            int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getId();
            //int nameId = Integer.parseInt(nameColumn.getText().trim());
            int hours = Integer.parseInt(txtFieldHours.getText().trim());
            VolunteerModel.getInstance().addHours(new CheckIn(dateTime, guildsId, nameId, hours));
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
            hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
            dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursManagerTable.setItems(volunteerModel.getAllCheckIns());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText(nameManagerTable.getSelectionModel().getSelectedItem().getName() + " har bidraget med " + txtFieldHours.getText() + " timer");
            txtFieldHours.clear();
            alert.showAndWait();

        } else if (datePicker.getValue() == null || guildManagerTable.getSelectionModel().getSelectedItem() == null || nameManagerTable.getSelectionModel().getSelectedItem() == null || txtFieldHours.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbejde, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }

    /**
     * Method for deleting the selected hours for a volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void deleteHoursButton(ActionEvent event) throws SQLException {
        if (hoursManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette deres timer .");
            alert.showAndWait();
        }
        if (hoursManagerTable.getSelectionModel().getSelectedItem() != null) {
            CheckIn selectedItem = hoursManagerTable.getSelectionModel().getSelectedItem();
            checkIn = selectedItem;
            volunteerModel.deleteHours(checkIn);
            hoursManagerTable.getItems().remove(selectedItem);
            hoursManagerTable.getSelectionModel().clearSelection();
        }
    }

    /**
     * Populates a list of hours/timeStamps for a clicked volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void handleVolunteersHours(MouseEvent event) throws SQLException, IOException {
        if (nameManagerTable.getSelectionModel().getSelectedItem() != null) {

            if (event.isPrimaryButtonDown() == false) {
                hoursManagerTable.getColumns().get(0).setVisible(true);
                hoursManagerTable.getColumns().get(1).setVisible(true);
                
                int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
                int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getId();
                volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
                hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
                dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
                hoursManagerTable.setItems(volunteerModel.getAllCheckIns().sorted());
            }
        }
    }

    /**
     * Opens the VolunteerInfo view and passes id, name, email, phoneNumber and
     * guildsId on the clicked volunteer into VolunteerInfoController.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleInfo(ActionEvent event) throws SQLException, IOException {
        if (nameManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig før du kan se informationer om dem.");
            alert.showAndWait();
        }

        if (nameManagerTable.getSelectionModel().getSelectedItem() != null) {
            Stage stage = new Stage();
            Parent root;
            try {
                int id = nameManagerTable.getSelectionModel().getSelectedItem().getId();
                String name = nameManagerTable.getSelectionModel().getSelectedItem().getName();
                String email = nameManagerTable.getSelectionModel().getSelectedItem().getEmail();
                String phoneNumber = nameManagerTable.getSelectionModel().getSelectedItem().getPhoneNumber();
                int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
                //int guildsId = nameManagerTable.getSelectionModel().getSelectedItem().getGuildsId();

                //int gId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
                //String gName = guildManagerTable.getSelectionModel().getSelectedItem().getName();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/VolunteerInfoView.fxml"));
                root = loader.load();
                VolunteerInfoViewController controller = loader.getController();
                controller.doMagicStuff(new Volunteer(id, name, email, phoneNumber, guildsId));
                //controller.getGuild(new Guild(gId, gName));
                Scene scene = new Scene(root);
                stage.setTitle("Rediger frivillig");
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);

                stage.setScene(scene);
                stage.show();

                stage = (Stage) ManagerScreen.getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                System.out.println("HandleInfo");
            }
        }
    }
//    private void backgroundColor() {
//        guildManagerColumn.setCellFactory((TableColumn<Guild, String> p) -> new TableCell<Guild, String>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (!isEmpty()) {
//                    this.setStyle("-fx-background-color: green;");
//                    setText(item);
//                }
//            }
//        });
//        nameManagerColumn.setCellFactory((TableColumn<Volunteer, String> p) -> new TableCell<Volunteer, String>() {
//            @Override
//            public void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if (!isEmpty()) {
//                    this.setStyle("-fx-background-color: green;");
//                    setText(item);
//                }
//            }
//        });
//        hoursManagerColumn.setCellFactory((TableColumn<CheckIn, Integer> p) -> new TableCell<CheckIn, Integer>() {
//            @Override
//            public void updateItem(Integer item, boolean empty) {
//                super.updateItem(item, empty);
//                if (!isEmpty()) {
//                    this.setStyle("-fx-background-color: green;");
//                    setText(String.valueOf(item));
//                }
//            }
//        });
//        dateManagerColumn.setCellFactory((TableColumn<CheckIn, Timestamp> p) -> new TableCell<CheckIn, Timestamp>() {
//            @Override
//            public void updateItem(Timestamp item, boolean empty) {
//                super.updateItem(item, empty);
//                if (!isEmpty()) {
//                    this.setStyle("-fx-background-color: green;");
//                    setText(String.valueOf(item));
//                }
//            }
//        });
//    }

    @FXML

    private void searchNameList(KeyEvent event) throws SQLException {

        String query = searchNameField.getText().trim();
        List<String> searchResult = null;
        SearchPattern searchStrategy;
        searchStrategy = new ContainsSearch(query);
        int guildId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
        searchResult = bllFacade.search(searchStrategy, guildId);
        volunteerModel.setFilteredNames(searchResult);
        nameManagerTable.setItems(volunteerModel.getNames().sorted());
    }

}
