/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import museumvolunteer.Model.AdminModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LoginScreenControllerTest
{
    private AdminModel adminModel;
    private int currentUser;
    private boolean test = false;
    
    private String usernameField = "Hans";
    private String passwordField = "1";
/**
 *
 * @author patrick
 */

    public LoginScreenControllerTest(){
        adminModel = new AdminModel();
    }
    

  @Test
    public void completeTestSignInManager()
    {

        
        assertEquals(adminModel.getAllManagersMock().get(1).getUsername(), usernameField.trim());
        assertEquals(adminModel.getAllManagersMock().get(1).getPassword(), passwordField.trim());
        
    }
    

  @Test
    public void failTestSignInManager()
    {

        
        assertEquals(adminModel.getAllManagersMock().get(2).getUsername(), usernameField.trim());
        assertEquals(adminModel.getAllManagersMock().get(2).getPassword(), passwordField.trim());
        
    }
    /**
     * Test of initialize method, of class LoginScreenController.
     */
//    @Test
//    public void testInitialize() {
//        System.out.println("initialize");
//        URL url = null;
//        ResourceBundle rb = null;
//        LoginScreenController instance = new LoginScreenController();
//        instance.initialize(url, rb);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of handleEnterPressed method, of class LoginScreenController.
     */

}