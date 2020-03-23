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
public class Room {
    private int id;
    private String name;
    private int x;
    private int y;
    private int point;
    
    private Room N = null;
    private Room E = null;
    private Room S = null;
    private Room W = null;
    
    static private Room wall = new Room(-1,"Wall",-1,-1,-1);;
    //private ArrayList<Room> neighbors = new ArrayList<Room>();
    
    public Room(int id, int x, int y, int point){
        this.id = id;
        this.name = "Default";
        this.x = x;
        this.y = y;
        this.point = point;
    }
    public Room(int id, String name, int x, int y, int point){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.point = point;
    }
    
//    public ArrayList<Room> getNeighbors(){
//        return this.neighbors;
//    }
    public void setN(Room a){
        this.N = a;
    }
    public void setE(Room a){
        this.E = a;
    }
    public void setS(Room a){
        this.S = a;
    }
    public void setW(Room a){
        this.W = a;
    }
    
    public Room getN(){
        return this.N;
    }
    public Room getE(){
        return this.E;
    }
    public Room getS(){
        return this.S;
    }
    public Room getW(){
        return this.W;
    }
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getId(){
        return this.id;
    }
    public int getPoint(){
        return this.point;
    }
    public String getName(){
        return this.name; 
    }
    static public Room getWall(){
        return Room.wall;
    }
}
