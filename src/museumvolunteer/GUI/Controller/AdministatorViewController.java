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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    
}
