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
public class Agent {
    private String name;
    private float gamma;
    private Building trainedTo = null;
    private ArrayList<ArrayList<Integer>> Q = new ArrayList<ArrayList<Integer>>();
    
    public Agent(){
        
    }
    
    public void printQ(){
        
    }
}
