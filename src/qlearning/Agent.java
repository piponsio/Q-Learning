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
        this.generateQ();
    }
    public void generateQ(){
        System.out.println();
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
        System.out.println();
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
    private Room randomUpdateQ(){
        
        Room randomNeighbour = Room.randomNeighbour(this.actualRoom);
        if(randomNeighbour.getId() != -2){
            ArrayList<Room> options = Room.neighborsAvailable(randomNeighbour);

            int newQvalue = (int) (this.trainedTo.getOfR(this.actualRoom.getId(), randomNeighbour.getId()) + this.gamma * this.maxQ(randomNeighbour,options)); 
            this.insertInQ(newQvalue, this.actualRoom.getId(), randomNeighbour.getId());
        }
        return randomNeighbour;
    }
    
    public int maxQ(Room room, ArrayList<Room> neighbour){
        ArrayList<Coords> coords = new ArrayList<Coords>();
        for(int i = 0; i < neighbour.size(); i++){
            Coords temp = new Coords(room.getId(), neighbour.get(i).getId());
            coords.add(temp);
        }
        return this.maxQ(coords);
    }
    public int maxQ(ArrayList<Coords> coords){
        int maxQ = 0;
        for(int i = 0; i < coords.size(); i++){
            if(this.getOfQ(coords.get(i).getX(), coords.get(i).getY()) > maxQ) maxQ = this.getOfQ(coords.get(i).getX(), coords.get(i).getY()); 
        }
        return maxQ;
    }
    private void move(Room room){
        this.actualRoom = room;
    }
    
    public void explorer(int maxCount, int iteration){
        for(int i = 0; i < iteration; i++){
            System.out.println();
            System.out.print("Iteration: ");
            System.out.println(i);
            this.explorer(maxCount);
            this.actualRoom = this.trainedTo.randomRoom();
        }
    }
    public void explorer(int maxCount){
        int count = 0;
        Room next;
        while(count < maxCount && !this.trainedTo.getGoal().equals(this.actualRoom )){
            next = this.randomUpdateQ();
            if(next.getId()==-2){
                System.out.println("Agente sin movimientos posibles");
                count = maxCount;
            }
            else{
                this.move(next);
                count++;
            }
            System.out.print("Movimiento: ");
            System.out.print(count);
            System.out.print(" ");
            System.out.print(this.name);
            System.out.print(", Habitación: ");
            System.out.println(this.actualRoom.getId());
            
            if(count>=maxCount){
                System.out.println();
                System.out.println("Máximos intentos realizados");
            }
            if(this.trainedTo.getGoal().equals(this.actualRoom )){
                System.out.println();
                System.out.println("Meta Encontrada");
            }
            
            
        }
        
    }
    private void run(){
        
    }   
    public void insertInQ(int value, int room, int action){
        this.Q.get(room).set(action,value);
    }
    public int getOfQ(int room, int action){
        return this.Q.get(room).get(action);
    }
}
