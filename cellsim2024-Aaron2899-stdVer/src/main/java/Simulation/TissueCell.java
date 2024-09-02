package Simulation;


import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 * A tissue cell. It wants to grow, but not as much as cancer. Has a chance to turn a dead
 * cell into a live one every time step
 */



public class TissueCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */
    int x;
    int y;

    public TissueCell(Pair coords){
        super(0,coords.getX(), coords.getY(), 1);
        this.x = coords.getX();
        this.y = coords.getY();
    }

    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors){
        double rngNum = 0.0;
        int topleft = 0;
        int top = 0;
        int topright = 0;
        int left = 0;
        int right = 0;
        int bottomleft = 0;
        int bottom = 0;
        int bottomright =0;

        ArrayList<Integer>deadCount = new ArrayList<>();

        //get the index from the coord, only for valid coordinates
        if(Calculator.indexFromCoord(x-1,y+1)>0){
            topleft = Calculator.indexFromCoord(x-1,y+1);
        }
        if(Calculator.indexFromCoord(x,y+1)>0){
            top = Calculator.indexFromCoord(x,y+1);
        }
        if(Calculator.indexFromCoord(x+1,y+1)>0){
            topright = Calculator.indexFromCoord(x+1,y+1);
        }
        if(Calculator.indexFromCoord(x-1,y)>0){
            left = Calculator.indexFromCoord(x-1,y);
        }
        if(Calculator.indexFromCoord(x+1,y)>0){
            right = Calculator.indexFromCoord(x+1,y);
        }
        if(Calculator.indexFromCoord(x-1,y-1)>0){
            bottomleft = Calculator.indexFromCoord(x-1,y-1);
        }
        if(Calculator.indexFromCoord(x,y-1)>0){
            bottom = Calculator.indexFromCoord(x,y-1);
        }
        if(Calculator.indexFromCoord(x+1,y-1)>0){
            bottomright = Calculator.indexFromCoord(x+1,y-1);
        }

        //use this to check all the neighborhood, find out if the indexed cell is dead
        //if the cell is dead, add the index of that cell to a list

        if(neighbors.get(topleft).getID() == 0){
            deadCount.add(topleft);
        }
        if(neighbors.get(top).getID()==0){
            deadCount.add(top);
        }
        if(neighbors.get(topright).getID()==0){
            deadCount.add(topright);
        }
        if(neighbors.get(left).getID()==0){
            deadCount.add(left);
        }
        if(neighbors.get(right).getID()==0){
            deadCount.add(right);
        }
        if(neighbors.get(bottomleft).getID()==0){
            deadCount.add(bottomleft);
        }
        if(neighbors.get(bottom).getID()==0){
            deadCount.add(bottom);
        }
        if(neighbors.get(bottomright).getID()==0){
            deadCount.add(bottomright);
        }

        //loop through that list for the number of items on that list, until a dead cell is grown into. When that happens, the counter is
        //raised to the limit, hence ending the loop

        for(int i = 0; i<deadCount.size(); i++){
                rngNum = Math.random()*100;
                if(rngNum<70.0){ //if it is dead 7/10 chance that it will grow into it
                    neighbors.set(deadCount.get(i),new TissueCell(Calculator.coordFromIndex(deadCount.get(i))));
                    i = deadCount.size(); //if it grows into it, then terminate interact Neighbours
                }
        }


        deadCount.clear();
        }






    }


