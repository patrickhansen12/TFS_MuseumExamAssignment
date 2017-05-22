
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BLL.BLLFacade;
import museumvolunteer.GUI.Model.AdminModel;

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
    
    private int currentUser;
    
    private AdminModel adminModel;
    
    private BLLFacade bllFacade;

    /**
     * 
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public LoginScreenController() throws IOException, SQLException
    {
        adminModel = AdminModel.getInstance();
        bllFacade = new BLLFacade();
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
     
        usernameField.setText("Svend123");
        passwordField.setText("123Svend");
    }

    @FXML
    private void LogInButton(MouseEvent event) throws IOException 
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
        for (int i = 0; i <= adminModel.getAllManagers().size() - 1; i++)
        {
            currentUser = i;
            
            if (usernameField.getText().trim().equals(adminModel.getAllManagers().get(currentUser).getUsername()) && passwordField.getText().trim().equals(adminModel.getAllManagers().get(currentUser).getPassword()))
            {
                logIn();
            }
        }
        for(int i = 0; i <= adminModel.getAllAdmins().size() - 1; i++)
        {
            currentUser = i;
            
            if (usernameField.getText().trim().equals(adminModel.getAllAdmins().get(currentUser).getUsername()) && passwordField.getText().trim().equals(adminModel.getAllAdmins().get(currentUser).getPassword()))
            {
                logInA();
            }
        }
            if (usernameField.getText().isEmpty()) 
            {
                publicMessageLabel.setText("Venlist indtast brugernavn");
            }
            else if (passwordField.getText().isEmpty()) 
            {
                publicMessageLabel.setText("Venlist indtast kodeord");
            }
            else
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
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
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