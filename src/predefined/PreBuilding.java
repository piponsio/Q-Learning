/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predefined;

import java.util.ArrayList;
import qlearning.Agent;
import qlearning.Building;

/**
 *
 * @author sparrow
 */
public class PreBuilding {    
    static private ArrayList<Building> buildings = new ArrayList<Building>();

    private PreBuilding(){
    }
    static public void start(){
                
        Building b1 = new Building("Edificio 1",0,4,9,16,false,false);
        b1.editDoor(0, 1, 'E', "add");
        b1.editDoor(0, 9, 'S', "add");
        b1.editDoor(1, 10, 'S', "add");
        b1.editDoor(9, 18, 'S', "add");
        b1.editDoor(18, 19, 'E', "add");
        b1.editDoor(19, 28, 'S', "add");
        b1.editDoor(28, 27, 'W', "add");
        b1.editDoor(28, 29, 'E', "add");
        b1.editDoor(29, 20, 'N', "add");
        b1.editDoor(20, 11, 'N', "add");
        b1.editDoor(10, 11, 'E', "add");
        b1.editDoor(11, 2, 'N', "add");
        b1.editDoor(2, 3, 'E', "add");
        b1.editDoor(3, 4, 'E', "add");
        b1.editDoor(4, 5, 'E', "add");
        b1.editDoor(5, 6, 'E', "add");
        b1.editDoor(6, 7, 'E', "add");
        b1.editDoor(7, 8, 'E', "add");
        b1.editDoor(11, 12, 'E', "add");
        b1.editDoor(12, 21, 'S', "add");
        b1.editDoor(21, 30, 'S', "add");
        b1.editDoor(21, 22, 'E', "add");
        b1.editDoor(22, 23, 'E', "add");
        b1.editDoor(23, 24, 'E', "add");
        b1.editDoor(24, 25, 'E', "add");
        b1.editDoor(4, 13, 'S', "add");
        b1.editDoor(13, 22, 'S', "add");
        b1.editDoor(22, 31, 'S', "add");
        b1.editDoor(31, 32, 'E', "add");
        b1.editDoor(32, 33, 'E', "add");
        b1.editDoor(5, 14, 'S', "add");
        b1.editDoor(14, 15, 'E', "add");
        b1.editDoor(7, 16, 'S', "add");
        b1.editDoor(16, 25, 'S', "add");
        b1.editDoor(16, 17, 'E', "add");
        b1.editDoor(17, 8, 'N', "add");
        b1.editDoor(17, 26, 'S', "add");
        b1.editDoor(34, 35, 'E', "add");
        
        b1.generateR();
        
        buildings.add(b1);


    }
    static public Building getBuildings(int i){
        return PreBuilding.buildings.get(i);
    }
}
