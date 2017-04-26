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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane logInScreen;

    public LoginScreenController()
    {
        
    }
    
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
    
    @FXML
    public void handleEnterPressed(KeyEvent event) throws IOException
    {
        if (event.getCode() == KeyCode.ENTER) 
        {
            signIn();
        }
    }
    
    private void signIn()throws IOException
    {   
        if (Brugernavn.getText().equals("t") && (Kodeord.getText().equals("t")))
        {
            publicMessageLabel.setText("");
            logIn();
            Brugernavn.clear();
            Kodeord.clear();
        }
        else if (Brugernavn.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast brugernavn");
        }
        else if (Kodeord.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast kodeord");
        }
        else if (!Brugernavn.getText().equals("t") || !Brugernavn.getText().equals("t"))
        {
            publicMessageLabel.setText("Forkert brugernavn eller kodeord");
        }
    }
    
    private void logIn() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Logged in as " + Brugernavn.getText());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
        
        stage = (Stage) logInScreen.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void tilbageLogIn(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        stage = (Stage) logInScreen.getScene().getWindow();
        stage.close();
    }
}
