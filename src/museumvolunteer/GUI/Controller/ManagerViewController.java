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
public class ManagerViewController implements Initializable {

    @FXML
    private TableView<?> LaugManagerTable;
    @FXML
    private TableColumn<?, ?> LaugManagerColumn;
    @FXML
    private TableView<?> NavneManagerTable;
    @FXML
    private TableColumn<?, ?> NavneManagerColumn;
    @FXML
    private TableView<?> TimerManagerTable;
    @FXML
    private TableColumn<?, ?> TimerManagerColumn;
    @FXML
    private AnchorPane ManagerScreen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void TilføjFrivilligeButton(ActionEvent event) {
    }

    @FXML
    private void SletFrivilligeButton(ActionEvent event) {
    }

    @FXML
    private void TilføjTimerButton(ActionEvent event) {
    }

    @FXML
    private void SletTimerButton(ActionEvent event) {
    }

    @FXML
    private void TilbageManager(ActionEvent event) throws IOException 
    {
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
    
}
