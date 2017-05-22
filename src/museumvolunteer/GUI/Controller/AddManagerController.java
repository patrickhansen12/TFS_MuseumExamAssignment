package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Manager;
import museumvolunteer.GUI.Model.AdminModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AddManagerController implements Initializable {

    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private AnchorPane addManagerScreen;
    @FXML
    private Label nameManager;
    @FXML
    private Label emailManager;
    @FXML
    private Label phoneNumberManager;
    
    private AdminModel adminModel;
    private Manager manager;
    @FXML
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public AddManagerController() throws IOException, SQLException {
        adminModel = AdminModel.getInstance();
    }

    

    @FXML
    private void performButton(ActionEvent event) throws SQLException {
        if (nameBox.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal give den nye manager et navn.");
            alert.showAndWait();
        } else {
            String name = nameBox.getText().trim();
            String email = emailBox.getText().trim();
            String phoneNumber = phoneBox.getText().trim();
            adminModel.addManager(new Manager(name, email, phoneNumber));
            nameBox.clear();
            nameBox.requestFocus();

            Stage stage = (Stage) addManagerScreen.getScene().getWindow();
            stage.close();
        }
    }
    

    @FXML
    private void returnButton(ActionEvent event) {
    }

    
}
