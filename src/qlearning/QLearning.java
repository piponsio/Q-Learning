/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlearning;

/**
 *
 * @author sparrow
 */
public class QLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Building b1 = new Building("Edificio 1",0,3,3,true);
        b1.printBuilding();
        b1.printR();
        
        Agent a1 = new Agent("Explorador 1",0.8f, b1, b1.randomRoom());
        a1.explorer(100,10);
        a1.printQ();


    }
    
}
