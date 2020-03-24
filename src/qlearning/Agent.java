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
    private Room actualRoom = null;
    
    public Agent(String name, float gamma, Building trainedTo, Room actualRoom, ArrayList<ArrayList<Integer>> Q){
        this.name = name;
        this.gamma = gamma;
        this.trainedTo = trainedTo;
        this.actualRoom = actualRoom;
        this.Q = Q;
    }
    
    public Agent(String name, float gamma, Building trainedTo, Room actualRoom){
        this.name = name;
        this.gamma = gamma;
        this.trainedTo = trainedTo;
        this.actualRoom = actualRoom;
        
        
    }
    public void generateQ(){
        System.out.println("Creando Matriz Q");
        int totalRoom = this.trainedTo.getTotalRoom();
        int count = 0;
        for(int i = 0; i < totalRoom; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int k = 0; k < totalRoom; k++) temp.add(0);
            this.Q.add(temp);
        }
    }
    public void printQ(){
        System.out.println("Imprimiendo Matriz Q");
        int totalRoom = this.Q.size();
        for(int i = 0; i < totalRoom; i++){
            for(int j = 0; j < totalRoom; j++){
                if(this.Q.get(i).get(j) < 0) System.out.print(" ");
                else if(this.Q.get(i).get(j) == 0) System.out.print("00");
                else if(this.Q.get(i).get(j) < 10 ) System.out.print("00");
                else if(this.Q.get(i).get(j) < 100 ) System.out.print("0");
                System.out.print(this.Q.get(i).get(j));
                System.out.print("  ");
            }
            System.out.println();   
        }
    }
    private void randomUpdateQ(){
    //busca un vecino al azar y calcula su nuevo qArrayList<Coords> coords = new ArrayList<Coords>();
        //generar lista de coords revisando pos actual(room,actuin), y matriz R
        
        //int newQvalue = (int) (this.trainedTo.getOfR(room, action) + this.gamma * this.maxQ(coords)); 
//        this.insertInQ((this), room, action);
    }
    private void move(Room room){
        
    }
    private void bestNeighbour(){
        
    }
    private void explorer(){
        
    }
    private void run(){
        
    }
    public void insertInQ(int value, int room, int action){
        this.Q.get(room).set(action,value);
    }
    public int getOfQ(int room, int action){
        return this.Q.get(room).get(action);
    }
    public int maxQ(ArrayList<Coords> coords){
        int maxQ = 0;
        for(int i = 0; i < coords.size(); i++){
            if(this.getOfQ(coords.get(i).getX(), coords.get(i).getY()) > maxQ) maxQ = this.getOfQ(coords.get(i).getX(), coords.get(i).getY()); 
        }
        return maxQ;
    }
}
