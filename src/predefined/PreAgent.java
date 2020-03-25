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
    
    private ArrayList<Agent> agents = new ArrayList<Agent>();
    
    public PreAgent(){
        //crear agentes prediseñados
        //Matriz Q creada
    }
    
    public Agent getAgent(int i){
        return this.agents.get(i);
    }
}
