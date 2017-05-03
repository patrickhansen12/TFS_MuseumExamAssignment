/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AddVolunteerController implements Initializable {

    @FXML
    private AnchorPane addScreen;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField guildBox;
    @FXML
    private TextArea noteBox;
    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, Integer> guildIdColumn;
    @FXML
    private TableColumn<Guild, String> guildColumn;

    private GuildsModel guildsModel;
    int guildToogle = 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        guildTable.setVisible(false);
    }    

    public AddVolunteerController() throws IOException, SQLException 
    {
        guildsModel = GuildsModel.getInstance();
    }
    

    @FXML
    private void performButton(ActionEvent event) throws SQLException, IOException
    {
        String name = nameBox.getText().trim();
        String email = emailBox.getText().trim();
        String phoneNumber = phoneBox.getText().trim();
        int guildsId = Integer.parseInt(guildBox.getText().trim());
        VolunteerModel.getInstance().addVolunteer(new Volunteer(name, email, phoneNumber, guildsId));
        nameBox.clear();
        nameBox.requestFocus();
        
        Stage stage = (Stage) addScreen.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void returnButton(ActionEvent event) 
    {
        Stage stage = (Stage) addScreen.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void openGuildList(ActionEvent event) 
    {         
     switch(guildToogle){
              case 1:
                 guildTable.setVisible(true); 
             guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
    guildIdColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getId()));
      guildTable.setItems(guildsModel.getGuilds());
      guildToogle = 2;      
   guildTable.getSelectionModel().clearSelection();
      break;
        
              case 2: 
                guildTable.getSelectionModel().clearSelection();  
            guildTable.setVisible(false);
            guildToogle = 1;
                 
            break;
     
        }
    }

    @FXML
    private void guildClicked(MouseEvent event) {
//int guildId = guildTable.getSelectionModel().getSelectedItem().getId();
 int guildIdBox =  guildTable.getSelectionModel().getSelectedItem().getId();
        guildBox.setText(""+guildIdBox);
    }

  
}

