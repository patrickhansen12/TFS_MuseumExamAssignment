/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AddVolunteerController implements Initializable {

    @FXML
    private AnchorPane AddScreen;
    @FXML
    private TextField NavneBox;
    @FXML
    private TextField EmailBox;
    @FXML
    private TextField TelefonBox;
    @FXML
    private TextField LaugBox;
    @FXML
    private TextArea NoterBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void udførButton(ActionEvent event) 
    {
        Stage stage = (Stage) AddScreen.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void tilbageTilføj(ActionEvent event) 
    {
        Stage stage = (Stage) AddScreen.getScene().getWindow();
        stage.close();
    }
    
}
