package Simulation;


import java.util.ArrayList;

/**
 * The default, boring cell.
 */

public class Cell {
    private int strength; //only a positive number for combat sim
    private int x; //coord of cell (must be at least 0)
    private int y; //coord of cell (must be at least 0)
    private int ID; //used to distinguish diff cell types (must be at least 0)

    public Cell(int strength, int x, int y, int ID) {
        setStrength(strength);
        setY(y);
        setID(ID);
        setX(x);
    }

    public void setStrength(int strength){
        if(strength>0){
            this.strength = strength;
        } else{
            this.strength = 0;
        }
    }

    public void setX(int x){
        if(x>=0){
            this.x = x;
        } else{
            this.x = 0;
        }
    }

    public void setY(int y){
        if(y>=0){
            this.y = y;
        } else{
            this.y = 0;
        }
    }

    public void setID(int ID){
        if(ID>=0){
            this.ID = ID;
        } else{
            this.ID = 0;
        }
    }

    public int getStrength(){
        return this.strength;
    }

    public int getX() {
        return this.x;
    }

    public int getY(){
        return this.y;
    }
    /**
     * the simulation expects a getter that return the ID that is specifically called getID()
     * any other method call will break it. So if you name this "getCellID()" or "getId()" it won't work
     * This is why interfaces are useful
     * @return
     */
    public int getID(){
        return this.ID;
    }

    public void interactNeighbors(ArrayList<Cell> neighbors){

    }
}
