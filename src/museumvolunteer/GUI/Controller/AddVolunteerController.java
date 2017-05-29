package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AddVolunteerController implements Initializable {

    @FXML
    private AnchorPane addScreen;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField guildBox;
    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, Integer> guildIdColumn;
    @FXML
    private TableColumn<Guild, String> guildColumn;
    @FXML
    private TextField guildNameBox;

    //private variables.
    private GuildsModel guildsModel;
    private VolunteerModel volunteerModel;
    int userAddVolunteer;

    /**
     * Initializes the AddVolunteerController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            guildColumn.setCellValueFactory(guildCol -> guildCol.getValue().getName());
            guildIdColumn.setCellValueFactory(guildIdCol -> guildIdCol.getValue().getId().asObject());
            guildTable.setItems(guildsModel.getGuilds());
            guildBox.setVisible(false);
            guildNameBox.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(AddVolunteerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets an instance of guildsModel and volunteerModel.
     *
     * @throws IOException
     * @throws SQLException
     */
    public AddVolunteerController() throws IOException, SQLException {
        guildsModel = new GuildsModel();
        volunteerModel = new VolunteerModel();
    }

    /**
     * Takes info from all textBoxes and adds a new volunteer to observable list
     * Volunteer and the database table Names.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void performButton(ActionEvent event) throws SQLException, IOException {
        if (nameBox.getText().isEmpty() || (guildBox.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal skrive både navn og laug, før du kan tilføje en ny volunteer.");
            alert.showAndWait();
        } else {
            String name = nameBox.getText().trim();
            String email = emailBox.getText().trim();
            String phoneNumber = phoneBox.getText().trim();
            int guildsId = Integer.parseInt(guildBox.getText().trim());
            volunteerModel.addVolunteer(new Volunteer(name, email, phoneNumber, guildsId));
            nameBox.clear();
            nameBox.requestFocus();

            if (userAddVolunteer == 0) {
                enterManagerView();
            } else if (userAddVolunteer == 1) {
                enterAdminView();
            }
        }
    }

    /**
     * Returns the manager to ManagerView.
     *
     * @param event
     */
    @FXML
    private void returnButton(ActionEvent event) throws IOException {
        if (userAddVolunteer == 0) {
            enterManagerView();
        } else if (userAddVolunteer == 1) {
            enterAdminView();
        }
    }

//    /**
//     * Method for showing guildManagerTable inside the "notes" windoow.
//     *
//     * @param event
//     */
//    @FXML
//    private void openGuildList(ActionEvent event) {
//        switch (guildToogle) {
//            case 1:
//                guildTable.setVisible(true);
//                guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
//                guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getId()));
//                guildTable.setItems(guildsModel.getGuilds());
//                guildToogle = 2;
//                guildTable.getSelectionModel().clearSelection();
//                break;
//
//            case 2:
//                guildTable.getSelectionModel().clearSelection();
//                guildTable.setVisible(false);
//                guildToogle = 1;
//
//                break;
//        }
//    }
    /**
     * if a guild is clicked inside the nameManagerTable, the guild name will be
     * put into the guildBox textfield.
     *
     * @param event
     */
    @FXML
    private void guildClicked(MouseEvent event) {

        if(guildTable.getSelectionModel().getSelectedItem().getName().getValue() != null)
        {
        String guildName = guildTable.getSelectionModel().getSelectedItem().getName().getValue();
        guildNameBox.setText(guildName);
        int guildIdBox = guildTable.getSelectionModel().getSelectedItem().getIdValue();
        guildBox.setText(String.valueOf(guildIdBox));
        
        if (guildName.isEmpty() || guildIdBox == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Slip ikke musen for hurtigt, når du scroller nedad lauglisten.");
            alert.showAndWait();
        }
        }
    }

    /**
     * sends the value of int currentUserAddVolunteer from either administratorView or ManagerView into instance variable userAddVolunteer.
     * @param currentUserAddVolunteer
     */
    public void pullCurrentUserAddVolunteer(int currentUserAddVolunteer) {
        userAddVolunteer = currentUserAddVolunteer;
    }

    /**
     * Enters the administratorView if userAddVolunteer == 1;
     * @throws IOException 
     */
    public void enterAdminView() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) addScreen.getScene().getWindow();
        stage2.close();
    }

    /**
     * Enters the managerView if userAddVolunteer == 0;
     * @throws IOException 
     */
    public void enterManagerView() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) addScreen.getScene().getWindow();
        stage2.close();
    }
}
