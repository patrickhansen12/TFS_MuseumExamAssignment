package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    private TextArea noteBox;
    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, Integer> guildIdColumn;
    @FXML
    private TableColumn<Guild, String> guildColumn;

    //private variables.
    private GuildsModel guildsModel;
    int guildToogle = 1;
    @FXML
    private TextField guildNameBox;

    /**
     * Initializes the AddVolunteerController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        guildTable.setVisible(false);
        guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
        guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getId()));
        guildTable.setItems(guildsModel.getGuilds());
        guildBox.setVisible(false);
        guildNameBox.setEditable(false);
        noteBox.setVisible(false);
    }

    /**
     * gets an instance of guildsModel.
     *
     * @throws IOException
     * @throws SQLException
     */
    public AddVolunteerController() throws IOException, SQLException {
        guildsModel = GuildsModel.getInstance();
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
        if (nameBox.getText().equals("") || (guildBox.getText().equals(""))) {
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
            VolunteerModel.getInstance().addVolunteer(new Volunteer(name, email, phoneNumber, guildsId));
            nameBox.clear();
            nameBox.requestFocus();

            Stage stage = (Stage) addScreen.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Returns the manager to ManagerView.
     *
     * @param event
     */
    @FXML
    private void returnButton(ActionEvent event) {
        Stage stage = (Stage) addScreen.getScene().getWindow();
        stage.close();
    }

    /**
     * Method for showing guildManagerTable inside the "notes" windoow.
     *
     * @param event
     */
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
    String guildName = guildTable.getSelectionModel().getSelectedItem().getName();
    guildNameBox.setText(""+guildName);
        int guildIdBox = guildTable.getSelectionModel().getSelectedItem().getId();
        guildBox.setText("" + guildIdBox);
    }
}
