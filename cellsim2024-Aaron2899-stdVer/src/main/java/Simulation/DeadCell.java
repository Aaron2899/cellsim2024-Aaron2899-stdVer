package Simulation;


import Util.Pair;

/**
 * This cell is dead and does nothing
 */
public class DeadCell extends Cell{

    /**
     * The logic object expects a constructor that takes a coordinate stored as a pair
     * See the Util folder and Pair.java to learn about the implementation of this
     * @param coords
     */
    int x;
    int y;

    public DeadCell(Pair coords){
       super(0,coords.getX(), coords.getY(), 0);
       this.x = coords.getX();
       this.y = coords.getY();
    }

}
