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

    public PreBuilding(){
        /*
        Building b1 = new Building("Edificio 1",0,3,10,true);
        b1.printBuilding();
        b1.printR();
        
        Agent a1 = new Agent("Explorador 1",0.8f, b1, b1.randomRoom());
        a1.explorer(100,10);
        a1.printQ();*/
    }
    
    static public Building getBuldings(int i){
        return PreBuilding.buildings.get(i);
    }
}
