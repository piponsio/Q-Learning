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
    //private Coords coords;
    private int width;
    private int height;
    private int point;
    
    private Room N = null;
    private Room E = null;
    private Room S = null;
    private Room W = null;
    
    static private Room wall = new Room(-1,"Wall",-1,-1,-1);;
    static private Room notNeighbour = new Room(-2,"notNeighbour",-1,-1,-1);;
    
    public Room(int id, int width, int height, int point){
        this.id = id;
        this.name = "Default";
        this.width = width;
        this.height = height;
        this.point = point;
    }
    public Room(int id, String name, int x, int y, int point){
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.point = point;
    }

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
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
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
    static public Room getNotNeighbour(){
        return Room.notNeighbour;
    }
    
    static public ArrayList<Room> neighborsAvailable(Room room){
        ArrayList<Room> options = new ArrayList<Room>();
        
        if(room.getN().getId() != -1) options.add(room.getN());
        if(room.getE().getId() != -1) options.add(room.getE());
        if(room.getS().getId() != -1) options.add(room.getS());
        if(room.getW().getId() != -1) options.add(room.getW());
        
        return options;
    }
    static public Room randomNeighbour(Room room){
        
        ArrayList<Room> options = Room.neighborsAvailable(room);
        int random = (int) Math.floor(Math.random()*((options.size()-1)-0+1)+0);

        Room randomNeighbour;
        randomNeighbour = (options.size() > 0) ? options.get(random) : Room.getNotNeighbour();
        return randomNeighbour;
    }
    
    static public Coords randomNeighbour(int id){
        Coords coords = null;
        
        return coords;
    }
    
    static public void bestNeighbour(){
        //agente lee Q y elige al mejor para moverse
    }
}
