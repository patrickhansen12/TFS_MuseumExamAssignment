/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import museumvolunteer.BE.ManagerMock;

/**
 *
 * @author Casper
 */
public class AdminDAOMock {
    
    /**
     * Reflects attributes for a manager in database table Managers.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private ManagerMock getOneManager(ResultSet rs) throws SQLException {
        String username = rs.getString("username");
        String password = rs.getString("password");
        return new ManagerMock(username, password);
    }
}
