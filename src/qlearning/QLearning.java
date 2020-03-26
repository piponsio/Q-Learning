/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlearning;

import predefined.PreAgent;
import predefined.PreBuilding;

/**
 *
 * @author sparrow
 */
public class QLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            PreBuilding.start();
            Building b1 = PreBuilding.getBuildings(0);
            
            b1.printBuilding();
            b1.printR();
            
            PreAgent.start();
            Agent a1 = PreAgent.getAgent(0);
            Agent a2 = PreAgent.getAgent(1);
            Agent a3 = PreAgent.getAgent(2);
            
            Agent a4 = PreAgent.getAgent(3);
            Agent a5 = PreAgent.getAgent(4);
            Agent a6 = PreAgent.getAgent(5);
            
            a1.setActualRoom(b1.getRoom(27));
            a2.setActualRoom(b1.getRoom(27));
            a3.setActualRoom(b1.getRoom(27));
            a4.setActualRoom(b1.getRoom(27));
            a5.setActualRoom(b1.getRoom(27));
            a6.setActualRoom(b1.getRoom(27));
            
            a1.printQ();
            a1.run(50,true,true);
            a2.printQ();
            a2.run(50,true,true);
            a3.printQ();
            a3.run(50,true,true);
            
            a4.printQ();
            a4.run(50,true,true);
            a5.printQ();
            a5.run(50,true,true);
            a6.printQ();
            a6.run(50,true,true);
            
            a5.setActualRoom(b1.randomRoom());
            a5.run(50,true,false);
            
            a5.setActualRoom(b1.randomRoom());
            a5.run(50,true,false);
            
            Building b2 = new Building("Edificio 2",0,4,9);
            b2.printBuilding();
            b2.printR();

            Agent a7 = new Agent("Explorador 7",0.8f, b2, b2.randomRoom());
            a7.explorer(100,100,false,false);
            a7.run(50,true,false);
            
            Building b3 = new Building("Edificio 3",0,10,10);

            Agent a8 = new Agent("Explorador 8",0.8f, b3, b3.randomRoom());
            a7.explorer(100,100,false,false);
            a7.run(50,true,false);
        
    }
    
}
