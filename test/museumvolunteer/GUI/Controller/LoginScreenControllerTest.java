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
    
    private String usernameField = "hej";
    private String passwordField = "goddag";
/**
 *
 * @author patrick
 */

    

  @Test
    private void testSignInManager()
    {
        for (int i = 0; i <= adminModel.getAllManagersMock().size() - 1; i++)
        {
            currentUser = i;
            
            if (usernameField.trim().equals(adminModel.getAllManagersMock().get(currentUser).getUsername()) && passwordField.trim().equals(adminModel.getAllManagersMock().get(currentUser).getPassword()))
            {
            
            }

        }
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