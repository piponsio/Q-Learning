/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlearning;

import java.util.ArrayList;

/**
 *
 * @author sparrow
 */
public class preBuilding {    
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public preBuilding(){
        //Crear edificios
    }
    
    public Building getBuldings(int i){
        return this.buildings.get(i);
    }
}
