package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class VolunteerInfoViewController implements Initializable {

    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneNumberBox;
    @FXML
    private TextField guildBox;
    @FXML
    private AnchorPane volunteerInfoScreen;
    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, Integer> guildIdColumn;
    @FXML
    private TableColumn<Guild, String> guildColumn;
    @FXML
    private TextField guildNameText;
    @FXML
    private TableView<Guild> currentGuildId;
    @FXML
    private TableColumn<Guild, String> currentGuildColumn;
    @FXML
    private TableColumn<Guild, Integer> currentGuildIdColumn;
    
    //instance variables.
    private VolunteerModel volunteerModel;
    private Volunteer thisVolunteer;
    private GuildsModel guildsModel;
    private TextField newGuildBox;
    public int userVolunteerInfo;

    /**
     * Initializes the VolunteerInfoViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getNameAsString()));
            guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getIdValue()));
            guildTable.setItems(guildsModel.getGuilds().sorted());
            guildBox.setVisible(false);

            guildNameText.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerInfoViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * instantiates volunteerModel and guildsModel.
     * @throws IOException
     * @throws SQLException 
     */
    public VolunteerInfoViewController() throws IOException, SQLException {
        volunteerModel = new VolunteerModel();
        guildsModel = new GuildsModel();
    }

    /**
     * Method for parsing data from administratorview/managerview and into the textfields.
     * @param v
     * @throws SQLException 
     */
    public void transferVolunteerInfo(Volunteer v) throws SQLException {
        thisVolunteer = v;
        currentGuildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getNameAsString()));
        currentGuildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getIdValue()));
        currentGuildId.setItems(guildsModel.getGuildsByNameId(v.getIdValue()).sorted());
        nameBox.setText(v.getNameAsString());
        emailBox.setText(v.getEmailAsString());
        phoneNumberBox.setText(v.getPhoneNumberAsString());
        guildBox.setText(String.valueOf(v.getGuildsIdValue()));
    }

    /**
     * Takes the values from the textboxes and updates the volunteer both in the
     * observable list, but also in the database.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void handleUpdate(ActionEvent event) throws SQLException, IOException {
        String name = nameBox.getText().trim();
        String email = emailBox.getText().trim();
        String phoneNumber = phoneNumberBox.getText().trim();
        volunteerModel.updateVolunteer(new Volunteer(thisVolunteer.getIdValue(), name, email, phoneNumber));
        if(userVolunteerInfo == 0)
        {
            enterManagerView();
        }
        else if(userVolunteerInfo == 1)
        {
            enterAdminView();
        }
    }

    /**
     * Return the manager to ManagerViewController.
     *
     * @param event
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @FXML
    public void returnButton(ActionEvent event) throws IOException, SQLException {
        if(userVolunteerInfo == 0)
        {
            enterManagerView();
        }
        else if(userVolunteerInfo == 1)
        {
            enterAdminView();
        }
    }

    /**
     * if a guild is clicked inside the nameManagerTable, the guild name will be
     * put into the guildBox textfield.
     *
     * @param event
     */
    @FXML
    private void guildClicked(MouseEvent event) {
        int guildId = guildTable.getSelectionModel().getSelectedItem().getIdValue();
        guildBox.setText("" + guildId);
        String guildNameBox = guildTable.getSelectionModel().getSelectedItem().getNameAsString();
        guildNameText.setText("" + guildNameBox);
        if (guildNameBox.isEmpty() || guildId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Slip ikke musen for hurtigt, når du scroller nedad lauglisten.");
            alert.showAndWait();
        }
    }

    /**
     * Grabs nameId and guildsId, then parses them into addToNewGuild in volunteerModel.
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void addToGuild(ActionEvent event) {
        try {
        int nameId = thisVolunteer.getIdValue();
        int guildsId = Integer.parseInt(guildBox.getText());
        volunteerModel.addToNewGuild(nameId, guildsId);
        currentGuildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getNameAsString()));
        currentGuildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getIdValue()));
        currentGuildId.setItems(guildsModel.getGuildsByNameId(nameId));
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(nameBox.getText() + " er nu tilføjet i " + guildNameText.getText());
        alert.showAndWait();
        }
        catch (SQLException sqlEx)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Samme laug");
            alert.setHeaderText(null);
            alert.setContentText("Du kan ikke tilføje en frivillig til et laug, vedkommende allerede er medlem af.");
            alert.showAndWait();
            System.out.println(sqlEx);
        }
    }

    /**
     * Method for recieving the value of currentUserVolunteerInfo from either administratorView or managerView.
     * @param currentUserVolunteerInfo 
     */
    public void pullCurrentUserVolunteerInfo(int currentUserVolunteerInfo) {
        userVolunteerInfo = currentUserVolunteerInfo;
    }
    
    /**
     * Method for entering administratorView if userVolunteerInfo == 1;
     * @throws SQLException
     * @throws IOException 
     */
    private void enterAdminView() throws SQLException, IOException
    {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) volunteerInfoScreen.getScene().getWindow();
        stage2.close();
    }
    
    /**
     * Method for entering managerView if userVolunteerInfo == 0;
     * @throws SQLException
     * @throws IOException 
     */
    private void enterManagerView() throws SQLException, IOException
    {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        Stage stage2 = (Stage) volunteerInfoScreen.getScene().getWindow();
        stage2.close();
    }
}
