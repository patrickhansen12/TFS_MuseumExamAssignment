package museumvolunteer.GUI.Controller;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class LoginScreenController implements Initializable {

    @FXML
    private TextField Brugernavn;
    @FXML
    private PasswordField Kodeord;
    @FXML
    private Label publicMessageLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void LogInButton(ActionEvent event) throws IOException 
    {
        signIn();
    }
    
    private void signIn()throws IOException
    {   
        if (Brugernavn.getText().equals("t") && (Kodeord.getText().equals("t")))
        {
            publicMessageLabel.setText("");
            Brugernavn.clear();
            Kodeord.clear();
            logIn();
        }
        else if (Brugernavn.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast brugernavn");
        }
        else if (Kodeord.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast kodeord");
        }
    }
    
    private void logIn() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Logged in as T");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();      
    }
}
