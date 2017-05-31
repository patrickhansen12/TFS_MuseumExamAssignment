package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;

    private AdminModel adminModel;

    /**
     * Initializes the AddManagerController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public AddManagerController() throws IOException, SQLException {
        adminModel = new AdminModel();
    }

    /**
     * Gets values from the textfields and parses them into addManager() in adminModel.
     * @param event
     * @throws SQLException
     * @throws IOException 
     */
    @FXML
    private void performButton(ActionEvent event) throws SQLException, IOException {
        if (nameBox.getText().equals("") || usernameBox.getText().equals("") || passwordBox.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal give den nye manager et navn, brugernavn, og adgangskode.");
            alert.showAndWait();
        } else {
            String name = nameBox.getText().trim();
            String email = emailBox.getText().trim();
            String phoneNumber = phoneBox.getText().trim();
            String username = usernameBox.getText().trim();
            String password = passwordBox.getText().trim();
            adminModel.addManager(new Manager(name, email, phoneNumber, username, password));

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Tovholder");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            Stage stage2 = (Stage) addManagerScreen.getScene().getWindow();
            stage2.close();
        }
    }

    /**
     * Returns the user to the administratorView.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void returnButton(ActionEvent event) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tovholder");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) addManagerScreen.getScene().getWindow();
        stage2.close();
    }
}
