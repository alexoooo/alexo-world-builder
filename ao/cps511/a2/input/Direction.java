package ao.cps511.a2.input;

import ao.cps511.a2.builder.Location;

/**
 *
 */
public enum Direction
{
    //--------------------------------------------------------------------
    NORTH( 1,  0),
    SOUTH(-1,  0),
    EAST ( 0,  1),
    WEST ( 0, -1);


    //--------------------------------------------------------------------
    private final int rowOffset;
    private final int colOffset;


    //--------------------------------------------------------------------
    private Direction(int rowOffset, int colOffset)
    {
        this.rowOffset = rowOffset;
        this.colOffset = colOffset;
    }


    //--------------------------------------------------------------------
    public Location offset(Location location)
    {
        return location.offset(rowOffset, colOffset);
    }
}
