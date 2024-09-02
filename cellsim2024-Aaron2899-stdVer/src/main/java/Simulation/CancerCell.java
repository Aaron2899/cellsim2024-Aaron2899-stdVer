package Simulation;


import Util.Calculator;
import Util.Pair;

import java.util.ArrayList;

/**
 *This is a cancer cell. It is the most complex cell as it can attack tissue or immune cells, or grow into a dead cell.
 * For attacking tissue, it is a 1 hit replace it with a dead cell.
 * Immune cells are cooler. Each hit from a cancer cell lowers its strength by 1. When an immune cell reaches 0 strength
 * it dies!
 *
 * It has a priority of action. If it can grow, it will grow. If it can kill a tissue cell, it will do that. Why?
 * Easiest way to grow is to kill a week tissue cell. If no other option, will attack immune cells. Path of
 * least resistance to growing basically.
 *
 * Growing means turning a dead cell into a CancerCell.
 */

public class CancerCell extends Cell{
    int x;
    int y;

    public CancerCell(Pair coords){
        super(1, coords.getX(), coords.getY(), 3);
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

        //list for storing neighborhood demographics
        ArrayList<Integer>deadCount = new ArrayList<>();
        ArrayList<Integer>tissueCount = new ArrayList<>();
        ArrayList<Integer>immuneCount = new ArrayList<>();

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

        //use the following to check neighborhood for tissue cells, add them to list
        if(neighbors.get(topleft).getID() == 1){
            tissueCount.add(topleft);
        }
        if(neighbors.get(top).getID()==1){
            tissueCount.add(top);
        }
        if(neighbors.get(topright).getID()==1){
            tissueCount.add(topright);
        }
        if(neighbors.get(left).getID()==1){
            tissueCount.add(left);
        }
        if(neighbors.get(right).getID()==1){
            tissueCount.add(right);
        }
        if(neighbors.get(bottomleft).getID()==1){
            tissueCount.add(bottomleft);
        }
        if(neighbors.get(bottom).getID()==1){
            tissueCount.add(bottom);
        }
        if(neighbors.get(bottomright).getID()==1){
            tissueCount.add(bottomright);
        }

        //use the following to check for immune cells, add them to counter list
        if(neighbors.get(topleft).getID() == 4){
            immuneCount.add(topleft);
        }
        if(neighbors.get(top).getID()==4){
            immuneCount.add(top);
        }
        if(neighbors.get(topright).getID()==4){
            immuneCount.add(topright);
        }
        if(neighbors.get(left).getID()==4){
            immuneCount.add(left);
        }
        if(neighbors.get(right).getID()==4){
            immuneCount.add(right);
        }
        if(neighbors.get(bottomleft).getID()==4){
            immuneCount.add(bottomleft);
        }
        if(neighbors.get(bottom).getID()==4){
            immuneCount.add(bottom);
        }
        if(neighbors.get(bottomright).getID()==4){
            immuneCount.add(bottomright);
        }

        //grow into one dead cell when available
       if(deadCount.size()>0) {
           for (int i = 0; i < deadCount.size(); i++) {
               neighbors.set(deadCount.get(i), new CancerCell(Calculator.coordFromIndex(deadCount.get(i))));
               i = deadCount.size();
           }
       }

        //kills tissue cell and replaces with dead cell if there are more tissue cells than immune cells
        if(tissueCount.size()>immuneCount.size() && tissueCount.size()>0){
            for(int i=0; i<tissueCount.size(); i++){
                neighbors.set(tissueCount.get(i), new DeadCell(Calculator.coordFromIndex(tissueCount.get(i))));
                i = tissueCount.size();
            }
        }

        //fights immune cell, kills it if hp = 0
        if(immuneCount.size()>0){
            for(int i=0; i<immuneCount.size(); i++) {
                int health = neighbors.get(immuneCount.get(i)).getStrength();
                neighbors.get(immuneCount.get(i)).setStrength(health-1);
                if(health-1 == 0){
                    neighbors.set(immuneCount.get(i), new DeadCell(Calculator.coordFromIndex(immuneCount.get(i))));
                }
                i = immuneCount.size();
            }
        }

        deadCount.clear();
        tissueCount.clear();
        immuneCount.clear();

    }

}
