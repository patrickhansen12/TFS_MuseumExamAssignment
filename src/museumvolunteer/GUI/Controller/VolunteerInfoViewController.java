package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label nameLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label phoneNumberLbl;
    @FXML
    private Button confirmButton;
    @FXML
    private Label guildLbl;
    @FXML
    private TextField guildBox;
    @FXML
    private AnchorPane volunteerInfoScreen;
int guildToogle = 2;
    //private variabler.
    private VolunteerModel volunteerModel;
    private Volunteer thisVolunteer;
    private GuildsModel guildsModel;
    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, Integer> guildIdColumn;
    @FXML
    private TableColumn<Guild, String> guildColumn;
    @FXML
    private TextField guildNameText;
    @FXML
    private Button guildBtn;

    /**
     * Initializes the VolunteerInfoViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
                guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getId()));
        guildTable.setItems(guildsModel.getGuilds());
guildBox.setVisible(false);
guildBtn.setVisible(false);
    guildNameText.setEditable(false);
    }

    /**
     * gets instances of volunteerModel and guildsModel through .getInstance()
     * since the models are singleton.
     *
     * @throws IOException
     * @throws SQLException
     */
    public VolunteerInfoViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }

    /**
     * gets a clicked volunteer from ManagerViewController and sets name, email,
     * phoneNumber and guildsId.
     *
     * @param v
     */
    public void doMagicStuff(Volunteer v) {
        thisVolunteer = v;
        nameBox.setText(v.getName());
        emailBox.setText(v.getEmail());
        phoneNumberBox.setText(v.getPhoneNumber());
        guildBox.setText(String.valueOf(v.getGuildsId()));
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
        int guildsId = Integer.parseInt(guildBox.getText());
        volunteerModel.updateVolunteer(new Volunteer(thisVolunteer.getId(), name, email, phoneNumber, guildsId));
        
        backVolunteerInfo(event);
    }

    /**
     * Return the manager to ManagerViewController.
     *
     * @param event
     */
    @FXML
    private void backVolunteerInfo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        root = loader.load();
        //controller.getGuild(new Guild(gId, gName));
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        stage = (Stage) volunteerInfoScreen.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void openGuildList(ActionEvent event) {
        switch (guildToogle) {
            case 1:
                guildTable.setVisible(true);
                guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
                guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getId()));
                guildTable.setItems(guildsModel.getGuilds());
                guildToogle = 2;
                guildTable.getSelectionModel().clearSelection();
                break;

            case 2:
                guildTable.getSelectionModel().clearSelection();
                guildTable.setVisible(false);
                guildToogle = 1;

                break;
        }
    }

    /**
     * if a guild is clicked inside the nameManagerTable, the guild name will be put into the guildBox textfield.
     * @param event 
     */
    @FXML
    private void guildClicked(MouseEvent event) {
int guildId = guildTable.getSelectionModel().getSelectedItem().getId();
guildBox.setText(""+ guildId);
        String guildNameBox = guildTable.getSelectionModel().getSelectedItem().getName();
        guildNameText.setText("" + guildNameBox);
    }
}
