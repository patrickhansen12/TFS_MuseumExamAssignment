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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class LoginScreenController implements Initializable {

    @FXML
    private AnchorPane logInScreen;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label publicMessageLabel;

    /**
     * 
     */
    public LoginScreenController()
    {
        
    }
    
    /**
     * Initializes the LoginScreenController class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    /**
     * Executes the method signIn().
     * @param event
     * @throws IOException 
     */
    @FXML
    private void LogInButton(ActionEvent event) throws IOException 
    {
        signIn();
    }
    
    /**
     * Signs you in when pressing the ENTER key.
     * @param event
     * @throws IOException
     */
    @FXML
    public void handleEnterPressed(KeyEvent event) throws IOException
    {
        if (event.getCode() == KeyCode.ENTER) 
        {
            signIn();
        }
    }
    
    /**
     * Temporary login details.
     * @throws IOException 
     */
    private void signIn()throws IOException
    {   
        if (usernameField.getText().equals("t") && (passwordField.getText().equals("t")))
        {
            publicMessageLabel.setText("");
            logIn();
            usernameField.clear();
            passwordField.clear();
        }
        else if (usernameField.getText().equals("a") && (passwordField.getText().equals("a")))
        {
            publicMessageLabel.setText("");
            logInA();
            usernameField.clear();
            passwordField.clear();
        }
        else if (usernameField.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast brugernavn");
        }
        else if (passwordField.getText().isEmpty()) 
        {
            publicMessageLabel.setText("Venlist indtast kodeord");
        }
        else if (!usernameField.getText().equals("t") || !usernameField.getText().equals("t"))
        {
            publicMessageLabel.setText("Forkert brugernavn eller kodeord");
        }
    }
    
    /**
     * Opens ManagerView if the correct username/password is submitted.
     * @throws IOException 
     */
    private void logIn() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/ManagerView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Logged in as " + usernameField.getText());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
        
        stage = (Stage) logInScreen.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Succesfully logs you in as admin to view AdministratorView.
     * @throws IOException 
     */
    private void logInA() throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministatorView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Logged in as " + usernameField.getText());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show(); 
        
        stage = (Stage) logInScreen.getScene().getWindow();
        stage.close();
    }

    /**
     * Return to mainView.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void returnLogIn(ActionEvent event) throws IOException 
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
