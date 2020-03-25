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
public class Building {
    private String name;
    private int id;
    private int x;
    private int y;
    private int totalRoom;
    private Room goal;
    private ArrayList<ArrayList<Integer>> R = null;
    private ArrayList<Room> rooms = new ArrayList<Room>();
  
    public Building(String name, int id, int x, int y, boolean randomDoors){
        this.random(name,id,x,y,randomDoors);
    }
    
    public void printBuilding(){
        for(int i = 0; i < this.x; i++){
            for(int j = 0; j < this.y; j++){
                System.out.print("---");
                if(rooms.get(i*this.y+j).getN().getId()!= -1) System.out.print("/  ");
                else System.out.print("---");
                System.out.print("---");
            }
            System.out.println();
            for(int j = 0; j < this.y; j++){
                System.out.print("|       |");
            }
            System.out.println();
            for(int j = 0; j < this.y; j++){
                if(rooms.get(i*this.y+j).getW().getId()!= -1) System.out.print("   ");
                else System.out.print("|  ");
                if(rooms.get(i*this.y+j).getId()<10) System.out.print("00");
                else if(rooms.get(i*this.y+j).getId()<100) System.out.print("0");
                else if(rooms.get(i*this.y+j).getId() == 0) System.out.print("0");
                System.out.print(rooms.get(i*this.y+j).getId());
                if(rooms.get(i*this.y+j).getE().getId()!= -1) System.out.print("   ");
                else System.out.print("  |");
            }
            System.out.println();
            for(int j = 0; j < this.y; j++){
                if(rooms.get(i*this.y+j).getW().getId()!= -1) System.out.print("/  ");
                else System.out.print("|  ");
                if(rooms.get(i*this.y+j).getPoint()<10) System.out.print("00");
                else if(rooms.get(i*this.y+j).getPoint()<100) System.out.print("0");
                else if(rooms.get(i*this.y+j).getPoint() == 0) System.out.print("0");
                System.out.print(rooms.get(i*this.y+j).getPoint());
                if(rooms.get(i*this.y+j).getE().getId()!= -1) System.out.print("  /");
                else System.out.print("  |");
            }
            System.out.println();
            for(int j = 0; j < this.y; j++){
                System.out.print("|       |");
            }
            System.out.println();
            for(int j = 0; j < this.y; j++){
                System.out.print("---");
                if(rooms.get(i*this.y+j).getS().getId()!=-1) System.out.print("/  ");
                else System.out.print("---");
                System.out.print("---");
            }
            System.out.println();
        }
    }
    //Funcion que imprime la matriz R
    public void printR(){
        System.out.println();
        System.out.println("Imprimiendo Matriz R");
        for(int i = 0; i < this.totalRoom; i++){
            for(int j = 0; j < this.totalRoom; j++){
                if(this.R.get(i).get(j) < 0) System.out.print(" ");
                else if(this.R.get(i).get(j) == 0) System.out.print("00");    
                System.out.print(this.R.get(i).get(j));
                System.out.print("  ");
            }
            System.out.println();   
        }
    }
    //Crea un edificio con habitaciones pegadas entre ellas algunas con
    //acceso a ellas y otras no
    private void random(String name, int id, int x, int y, boolean randomDoors){
        this.name = name;
        this.x = x;
        this.y = y;
        this.totalRoom = x * y;
        if(R == null) R = new ArrayList<ArrayList<Integer>>();
        
        int count = 0;
        
        int point = 0;
        
        System.out.println();
        System.out.println("Construyendo edificio");
        //Eligiendo la habitación que será la meta
        int random = (int) Math.floor(Math.random()*(this.totalRoom-1-0+1)+0);
        
        System.out.println();
        System.out.println("Creando habitaciones");
        //Creando habitaciones que están juntas entre sí como forma matriz 2x2
        //otorgandole el valor de 100 a la meta
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(random == count) point = 100;
                else point = 0;
                Room temp = new Room(count,i,j,point);
                if(point == 100) this.goal = temp;
                rooms.add(temp);
                count++;
            }
        }
        
        count = 0;
        point = 0;
        //Definiendo una muralla
        //Room wall = new Room(-1,"Wall",-1,-1,-1);
        System.out.println();
        System.out.println("Asignando Puertas");
        //Escogiendo si existen puertas en cada habitación hacía cada
        //coordenada (N,E,S,W) -> (N,E,S,O)
        for(int i = 0; i < this.totalRoom; i++){
            //Definiendo murallas para el perimetro del edificio
            if(rooms.get(i).getWidth() == 0) rooms.get(i).setN(Room.getWall());
            if(rooms.get(i).getHeight() == 0) rooms.get(i).setW(Room.getWall());
            if(rooms.get(i).getWidth() == x-1) rooms.get(i).setS(Room.getWall());
            if(rooms.get(i).getHeight() == y-1) rooms.get(i).setE(Room.getWall());
            
            if(randomDoors){
                //Definiendo si habrá puerta hacia el Este
                //y asignando a vecino o muralla
                boolean status;
                
                random = (int) Math.floor(Math.random()*(1-0+1)+0);
                if(rooms.get(i).getE()==null) status = (random == 0) ? this.editDoor(i,(i+1),'E',"bloq") : this.editDoor(i,(i+1),'E',"add");
                
                random = (int) Math.floor(Math.random()*(1-0+1)+0);
                if(rooms.get(i).getS()==null) status = (random == 0) ? this.editDoor(i,(i+y),'S',"bloq") : this.editDoor(i,(i+y),'S',"add");
              
                random = (int) Math.floor(Math.random()*(1-0+1)+0);
                if(rooms.get(i).getW()==null) status = (random == 0) ? this.editDoor(i,(i-1),'W',"bloq") : this.editDoor(i,(i-1),'W',"add");

                random = (int) Math.floor(Math.random()*(1-0+1)+0);
                if(rooms.get(i).getN() == null) status = (random == 0) ? this.editDoor(i,(i-y),'N',"bloq") : this.editDoor(i,(i-y),'N',"add");
               
            }
            else{
                rooms.get(i).setN(Room.getWall());
                rooms.get(i).setE(Room.getWall());
                rooms.get(i).setS(Room.getWall());
                rooms.get(i).setW(Room.getWall());
            }
        }
        
        this.generateR();
        
    }
    public Room randomRoom(){
        int random = (int) Math.floor(Math.random()*((this.totalRoom-1)-0+1)+0);
        return this.rooms.get(random);
    }
    private void generateR(){
        System.out.println();
        System.out.println("Creando Matriz R");
        //Asignando valores a matriz R
        for(int i = 0; i < this.totalRoom; i++){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int k = 0; k < this.totalRoom; k++) temp.add(-1);
            if(rooms.get(i).getN().getId() != -1) temp.set(rooms.get(i).getN().getId(),rooms.get(i).getN().getPoint());
            if(rooms.get(i).getE().getId() != -1) temp.set(rooms.get(i).getE().getId(),rooms.get(i).getE().getPoint());
            if(rooms.get(i).getS().getId() != -1) temp.set(rooms.get(i).getS().getId(),rooms.get(i).getS().getPoint());
            if(rooms.get(i).getW().getId() != -1) temp.set(rooms.get(i).getW().getId(),rooms.get(i).getW().getPoint());
            if(rooms.get(i).getPoint() == 100) temp.set(rooms.get(i).getId(),rooms.get(i).getPoint());
            R.add(temp);
        }
    }
    public void insertInR(int value, int room, int action){
        this.R.get(room).set(action,value);
    }
    public int getOfR(int room, int action){
        return this.R.get(room).get(action);
    }
    public int getTotalRoom(){
        return this.totalRoom;
    }
    public Room getGoal(){
        return this.goal;
    }
    public boolean editDoor(int a, int b, char c, String d){
        if(d.equals("add") || d.equals("ADD")){
            if(c == 'E' || c == 'e' ){
                this.rooms.get(a).setE(this.rooms.get(b));
                this.rooms.get(b).setW(this.rooms.get(a));
            }
            else if(c == 'S' || c == 's' ){
                this.rooms.get(a).setS(this.rooms.get(b));
                this.rooms.get(b).setN(this.rooms.get(a));
            }
            else if(c == 'W' || c == 'w' ){
                this.rooms.get(a).setW(this.rooms.get(b));
                this.rooms.get(b).setE(this.rooms.get(a));
            }
            else if(c == 'N' || c == 'n' ){
                this.rooms.get(a).setN(this.rooms.get(b));
                this.rooms.get(b).setS(this.rooms.get(a));
            }
        }
        else if(d.equals("bloq") || d.equals("BLOQ")){
            if(c == 'E' || c == 'e' ){
                rooms.get(a).setE(Room.getWall());
                rooms.get(b).setW(Room.getWall());   
            }
            else if(c == 'S' || c == 's' ){
                rooms.get(a).setS(Room.getWall());
                rooms.get(b).setN(Room.getWall());   
            }
            else if(c == 'W' || c == 'w' ){
                rooms.get(a).setW(Room.getWall());
                rooms.get(b).setE(Room.getWall());   
            }
            else if(c == 'N' || c == 'n' ){
                rooms.get(a).setN(Room.getWall());
                rooms.get(b).setS(Room.getWall());   
            }
        }
        return true;
    }
}