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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class VolunteerViewController implements Initializable {

    @FXML
    private TableView<?> LaugTable;
    @FXML
    private TableColumn<?, ?> LaugColumn;
    @FXML
    private TableView<?> NavneTable;
    @FXML
    private TableColumn<?, ?> NavneColumn;
    @FXML
    private TextField SøgNavnField;
    @FXML
    private TextField NoterTimerField;
    @FXML
    private Label ConfirmationLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void IndsætTimer(ActionEvent event) {
    }
    
}
