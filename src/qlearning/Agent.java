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
    /*
    public Agent(String name, float gamma, Building trainedTo, Room actualRoom, ArrayList<ArrayList<Integer>> Q){
        this.name = name;
        this.gamma = gamma;
        this.trainedTo = trainedTo;
        this.actualRoom = actualRoom;
        this.Q = Q;
    }
    */
    public Agent(String name, float gamma, Building trainedTo, Room actualRoom){
        this.name = name;
        this.gamma = gamma;
        this.trainedTo = trainedTo;
        this.actualRoom = actualRoom;
        this.generateQ();
    }
    public void generateQ(){
        System.out.println();
        System.out.println("Creando Matriz Q de "+this.name);
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
        System.out.print("X/X  ");
        for(int i = 0; i < totalRoom; i++){
                if(this.Q.get(i).get(0) < 0) System.out.print(" ");
                else if(i == 0) System.out.print("00");
                else if(i < 10 ) System.out.print("00");
                else if(i < 100 ) System.out.print("0");
                System.out.print(i);
                if(i != totalRoom-1)System.out.print("  ");       
        }
        System.out.println();
        for(int i = 0; i < totalRoom; i++){
                if(this.Q.get(i).get(0) < 0) System.out.print(" ");
                else if(i == 0) System.out.print("00");
                else if(i < 10 ) System.out.print("00");
                else if(i < 100 ) System.out.print("0");
                System.out.print(i);
                System.out.print("  ");
            for(int j = 0; j < totalRoom; j++){
                if(this.Q.get(i).get(j) < 0) System.out.print(" ");
                else if(this.Q.get(i).get(j) == 0) System.out.print("00");
                else if(this.Q.get(i).get(j) < 10 ) System.out.print("00");
                else if(this.Q.get(i).get(j) < 100 ) System.out.print("0");
                System.out.print(this.Q.get(i).get(j));
                if(j != totalRoom-1)System.out.print("  ");
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
    
    public void explorer(int maxCount, int iteration, boolean comment, boolean details){
        int count = 0;
        for(int i = 0; i < iteration; i++){
            if(comment) System.out.println();
            if(comment) System.out.print("Iteration: ");
            if(comment) System.out.println(i);
            if(comment) System.out.print("Init Room: ");
            if(comment) System.out.println(this.actualRoom.getId());
            count = this.explorer(maxCount, comment, details);
            if(comment) System.out.print("Total Mov: ");
            if(comment) System.out.println(count);
            this.actualRoom = this.trainedTo.randomRoom();
        }
    }
    public int explorer(int maxCount, boolean comment, boolean details){
        int count = 0;
        Room next;
        while(count < maxCount && !this.trainedTo.getGoal().equals(this.actualRoom )){
            next = this.randomUpdateQ();
            if(next.getId()==-2){
                if(comment) System.out.println("Agente sin movimientos posibles");
                count = maxCount;
            }
            else{
                this.move(next);
                count++;
            }
            if(details){
                System.out.print("Movimiento: ");
                System.out.print(count);
                System.out.print(" ");
                System.out.print(this.name);
                System.out.print(", Habitación: ");
                System.out.println(this.actualRoom.getId());
            }
            if(count>=maxCount && comment){
                System.out.println("Máximos intentos realizados");
            }
        }
        if(this.trainedTo.getGoal().equals(this.actualRoom )){
            if(comment){
                System.out.println("Meta Encontrada");
            }
        }
        return count;
        
    }
    public void run(int maxCount, boolean comment, boolean details){
        int count = 0;
        if(comment){
            System.out.println();
            System.out.println("Iniciando Recorrido de "+this.name);
            System.out.print("Room: ");
            System.out.println(this.actualRoom.getId());
        }
        while(count < maxCount && !this.actualRoom.equals(this.trainedTo.getGoal())){
            
            Room next = this.bestNeighbour(this.actualRoom);
            
            if(next.getId()==-2){
                if(comment) System.out.println("Agente sin movimientos posibles");
                count = maxCount;
            }
            else{
                if(details)
                for(int i = 0; i < Room.neighborsAvailable(this.actualRoom).size(); i++){
                   System.out.print(Room.neighborsAvailable(this.actualRoom).get(i).getId());
                   System.out.print("(");
                   System.out.print(this.getOfQ(this.actualRoom.getId(),Room.neighborsAvailable(this.actualRoom).get(i).getId()));
                   System.out.println(")");
                }
                this.move(next);
                count++;
            }
            
            if(comment){
                System.out.print("Room: ");
                System.out.println(this.actualRoom.getId());
            }
            if(this.trainedTo.getGoal().equals(this.actualRoom) && comment){
                System.out.println("Meta encontrada");
            }
        }       
        if(count >= maxCount && comment){
            System.out.println("Máximos intentos realizados");
        }
        if(comment) System.out.print("Total Mov: ");
        if(comment) System.out.println(count);
    }   
    public void insertInQ(int value, int room, int action){
        this.Q.get(room).set(action,value);
    }
    public int getOfQ(int room, int action){
        return this.Q.get(room).get(action);
    }
    public void setActualRoom(Room room){
        this.actualRoom = room;
    }
    private Room bestNeighbour(Room room){
        int bestQ = 0;
        Room best = null;
        ArrayList<Room> options = Room.neighborsAvailable(room);

        if(options.size() <= 0) best = Room.getNotNeighbour();
        else{
            for(int i = 0; i < options.size(); i++){
                if(bestQ == 0){
                    best = options.get(i);
                    bestQ = this.getOfQ(room.getId(),options.get(i).getId());
                }
                else if(this.getOfQ(room.getId(),options.get(i).getId()) > bestQ){
                    best = options.get(i);
                    bestQ = this.getOfQ(room.getId(),options.get(i).getId());
                }
            }
        }
        return best;
    }
}
