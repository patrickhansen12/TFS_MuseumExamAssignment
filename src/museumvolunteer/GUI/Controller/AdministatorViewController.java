/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AdministatorViewController implements Initializable {

    @FXML
    private TableView<?> LaugAdminTable;
    @FXML
    private TableColumn<?, ?> LaugAdminColumn;
    @FXML
    private TableView<?> NavneAdminTable;
    @FXML
    private TableColumn<?, ?> NavneAdminColumn;
    @FXML
    private TableView<?> TimerAdminTable;
    @FXML
    private TableColumn<?, ?> TimerAdminColumn;
    @FXML
    private TableView<?> ManagerAdminTable;
    @FXML
    private TableColumn<?, ?> ManagerAdminColumn;
    @FXML
    private AnchorPane AdminScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TilføjLaugButton(ActionEvent event) {
    }

    @FXML
    private void TilføjFrivilligeButton(ActionEvent event) {
    }

    @FXML
    private void SletLaugButton(ActionEvent event) {
    }

    @FXML
    private void SletFrivilligeButton(ActionEvent event) {
    }

    @FXML
    private void TilføjManagerButton(ActionEvent event) {
    }

    @FXML
    private void SletManagerButton(ActionEvent event) {
    }

    @FXML
    private void SletTimerButton(ActionEvent event) {
    }

    @FXML
    private void TilføjTimerButton(ActionEvent event) {
    }

    @FXML
    private void tilbageAdmin(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        stage = (Stage) AdminScreen.getScene().getWindow();
        stage.close();
    }
    
}
