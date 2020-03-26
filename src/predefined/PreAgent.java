/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predefined;

import java.util.ArrayList;
import qlearning.Agent;

/**
 *
 * @author sparrow
 */
public class PreAgent {
    
    static public ArrayList<Agent> agents = new ArrayList<Agent>();
    
    private PreAgent(){
        //crear agentes prediseñados
        //Matriz Q creada
    }
    static public void start(){
        Agent a1 = new Agent("Explorador 1",1.0f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        Agent a2 = new Agent("Explorador 2",0.8f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        Agent a3 = new Agent("Explorador 3",0.7f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        
        Agent a4 = new Agent("Explorador 4",1.0f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        Agent a5 = new Agent("Explorador 5",0.8f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        Agent a6 = new Agent("Explorador 6",0.6f, PreBuilding.getBuildings(0),PreBuilding.getBuildings(0).randomRoom());
        
        a1.explorer(100,10,false,false);
        a2.explorer(100,10,false,false);
        a3.explorer(100,10,false,false);
        
        a4.explorer(100,100,false,false);
        a5.explorer(100,100,false,false);
        a6.explorer(100,100,false,false);
        
        
        PreAgent.agents.add(a1);
        PreAgent.agents.add(a2);
        PreAgent.agents.add(a3);
        PreAgent.agents.add(a4);
        PreAgent.agents.add(a5);
        PreAgent.agents.add(a6);
    }
    static public Agent getAgent(int i){
        return PreAgent.agents.get(i);
    }
}
