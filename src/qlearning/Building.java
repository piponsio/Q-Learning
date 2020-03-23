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
    private ArrayList<ArrayList<Integer>> R = null;
    private ArrayList<Room> rooms = new ArrayList<Room>();
    
    public Building(String name, int id, int x, int y, int totalRoom, ArrayList<Room> rooms){
        this.name = name;
        this.id = id;
        this.x = x;
        this.y = y;
        this.totalRoom = totalRoom;
        this.rooms = rooms;
        this.generateR();
    }
    public Building(String name, int id, int x, int y){
        this.random(name,id,x,y);
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
    public void random(String name, int id, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
        this.totalRoom = x * y;
        if(R == null) R = new ArrayList<ArrayList<Integer>>();
        
        int count = 0;
        
        int point = 0;
        
        System.out.println("Construyendo edificio");
        //Eligiendo la habitación que será la meta
        int random = (int) Math.floor(Math.random()*(this.totalRoom-1-0+1)+0);
        
        System.out.println("Creando habitaciones");
        //Creando habitaciones que están juntas entre sí como forma matriz 2x2
        //otorgandole el valor de 100 a la meta
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(random == count) point = 100;
                else point = 0;
                Room temp = new Room(count,i,j,point);
                rooms.add(temp);
                count++;
            }
        }
        
        count = 0;
        point = 0;
        //Definiendo una muralla
        //Room wall = new Room(-1,"Wall",-1,-1,-1);
        System.out.println("Asignando Puertas");
        //Escogiendo si existen puertas en cada habitación hacía cada
        //coordenada (N,E,S,W) -> (N,E,S,O)
        for(int i = 0; i < this.totalRoom; i++){
            //Definiendo murallas para el perimetro del edificio
            if(rooms.get(i).getX() == 0) rooms.get(i).setN(Room.getWall());
            if(rooms.get(i).getY() == 0) rooms.get(i).setW(Room.getWall());
            if(rooms.get(i).getX() == x-1) rooms.get(i).setS(Room.getWall());
            if(rooms.get(i).getY() == y-1) rooms.get(i).setE(Room.getWall());
            
            //Definiendo si habrá puerta hacia el Este
            //y asignando a vecino o muralla
            random = (int) Math.floor(Math.random()*(1-0+1)+0);
            if(rooms.get(i).getE()==null){
                if(random == 0){
                    rooms.get(i).setE(Room.getWall());
                    rooms.get(i+1).setW(Room.getWall());
                }
                else{
                    rooms.get(i).setE(rooms.get(i+1));
                    rooms.get(i+1).setW(rooms.get(i));
                }
            }
            
            random = (int) Math.floor(Math.random()*(1-0+1)+0);
            if(rooms.get(i).getS()==null){
                if(random == 0){
                    rooms.get(i).setS(Room.getWall());
                    rooms.get(i+y).setN(Room.getWall());
                }
                else{
                    rooms.get(i).setS(rooms.get(i+y));
                    rooms.get(i+y).setN(rooms.get(i));
                }
            }
            
            random = (int) Math.floor(Math.random()*(1-0+1)+0);
            if(rooms.get(i).getW()==null){
                if(random == 0){
                    rooms.get(i).setW(Room.getWall());
                    rooms.get(i-1).setE(Room.getWall());
                }
                else{
                    rooms.get(i).setW(rooms.get(i-1));
                    rooms.get(i-1).setE(rooms.get(i));
                }
            }
            
            random = (int) Math.floor(Math.random()*(1-0+1)+0);
            if(rooms.get(i).getN()==null){
                if(random == 0){
                    rooms.get(i).setN(Room.getWall());
                    rooms.get(i-y).setS(Room.getWall());
                }
                else{
                    rooms.get(i).setN(rooms.get(i-y));
                    rooms.get(i-y).setS(rooms.get(i));
                }
            }   
        }
        
        this.generateR();
        
    }
    public void generateR(){
        System.out.println("Creando Matriz R");
        //Asignando valores a matriz R
        int count = 0;
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
    
}