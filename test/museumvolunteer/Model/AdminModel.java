/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.ManagerMock;

/**
 *
 * @author patrick
 */
public class AdminModel {
       private ObservableList<ManagerMock> allManagersMock = FXCollections.observableArrayList();
        public ObservableList<ManagerMock> getAllManagersMock()
    {
        ManagerMock managerMock2 = new ManagerMock("Allan", "1");
        ManagerMock managerMock3 = new ManagerMock("Hans", "2");
        ManagerMock managerMock4 = new ManagerMock("Ulla", "3");
        ManagerMock managerMock5 = new ManagerMock("Hermann", "4");
        ManagerMock managerMock6 = new ManagerMock("Benny", "5");
        ManagerMock managerMock7 = new ManagerMock("Bertha", "6");
        ManagerMock managerMock8 = new ManagerMock("Ulrik", "7");
        ManagerMock managerMock9 = new ManagerMock("Matilde", "8");
        ManagerMock managerMock10 = new ManagerMock("Henry", "9");
        ManagerMock managerMock11 = new ManagerMock("Susanne", "10");
        ManagerMock managerMock12 = new ManagerMock("Gurli", "11");
        ManagerMock managerMock13 = new ManagerMock("Magnus", "12");
        ManagerMock managerMock14 = new ManagerMock("Mikkel", "13");
        ManagerMock managerMock15 = new ManagerMock("Arne", "14");
        ManagerMock managerMock16 = new ManagerMock("Martin", "15");
        allManagersMock.add(managerMock2);
        allManagersMock.add(managerMock3);
        allManagersMock.add(managerMock4);
        allManagersMock.add(managerMock5);
        allManagersMock.add(managerMock6);
        allManagersMock.add(managerMock7);
        allManagersMock.add(managerMock8);
        allManagersMock.add(managerMock9);
        allManagersMock.add(managerMock10);
        allManagersMock.add(managerMock11);
        allManagersMock.add(managerMock12);
        allManagersMock.add(managerMock13);
        allManagersMock.add(managerMock14);
        allManagersMock.add(managerMock15);
        allManagersMock.add(managerMock16);
        return allManagersMock;
    }
    
}
