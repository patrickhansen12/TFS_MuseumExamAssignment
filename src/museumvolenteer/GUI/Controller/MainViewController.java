package museumvolenteer.GUI.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class MainViewController implements Initializable {
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void FrivilligButton(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/VolunteerView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ManagerButton(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
    
}
