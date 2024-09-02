package Simulation;

import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 * The immune cell! It kills cancer, and has a chance to attack multiple cancer cells per turn!
 */

public class ImmuneCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */

    int x;
    int y;

    public ImmuneCell(Pair coords){
        super(3, coords.getX(), coords.getY(),4);
        this.x = coords.getX();
        this.y = coords.getY();
    }

    @Override
    public void interactNeighbors(ArrayList<Cell> neighbors){
        int topleft = 0;
        int top = 0;
        int topright = 0;
        int left = 0;
        int right = 0;
        int bottomleft = 0;
        int bottom = 0;
        int bottomright =0;

        double rngNum =0.0;

        ArrayList<Integer>cancerCount = new ArrayList<>();

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

        //check for cancer cells and add to the cancer counter
        if(neighbors.get(topleft).getID() == 3){
            cancerCount.add(topleft);
        }
        if(neighbors.get(top).getID()==3){
            cancerCount.add(top);
        }
        if(neighbors.get(topright).getID()==3){
            cancerCount.add(topright);
        }
        if(neighbors.get(left).getID()==3){
            cancerCount.add(left);
        }
        if(neighbors.get(right).getID()==3){
            cancerCount.add(right);
        }
        if(neighbors.get(bottomleft).getID()==3){
            cancerCount.add(bottomleft);
        }
        if(neighbors.get(bottom).getID()==3){
            cancerCount.add(bottom);
        }
        if(neighbors.get(bottomright).getID()==3){
            cancerCount.add(bottomright);
        }

        //kills cancer
        for(int i=0; i<cancerCount.size(); i++){
            neighbors.set(cancerCount.get(i), new DeadCell(Calculator.coordFromIndex(cancerCount.get(i))));
            rngNum = Math.random()*100;
            if(rngNum>50.0){
                i = cancerCount.size();
            }
        }
    cancerCount.clear();
    }
}