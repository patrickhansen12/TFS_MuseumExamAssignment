package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class MainViewController implements Initializable {

    @FXML
    private AnchorPane MainScreen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    /**
     * Sends the user into VolunteerView.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void volunteerButton(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/VolunteerView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        closeWindow();
    }

    /**
     * Button for ManagerView to open. You need username and password to get further than loginScreen.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void managerButton(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        closeWindow();
    }
    
    /**
     * Close the MainView.
     */
    private void closeWindow()
    {
        Stage stage = (Stage) MainScreen.getScene().getWindow();
        stage.close();
    }
}
