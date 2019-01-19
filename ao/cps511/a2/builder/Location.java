package ao.cps511.a2.builder;

/**
 *
 */
public class Location
{
    //--------------------------------------------------------------------
    public static final Location ORIGIN = new Location(0, 0);


    //--------------------------------------------------------------------
    private final int row;
    private final int col;


    //--------------------------------------------------------------------
    public Location(int row, int column)
    {
        this.row = row;
        this.col = column;
    }


    //--------------------------------------------------------------------
    public int row()
    {
        return row;
    }

    public int column()
    {
        return col;
    }


    //--------------------------------------------------------------------
    public Location offset(int rowOffset, int colOffset)
    {
        return new Location(row + rowOffset,
                            col + colOffset);
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return "[" + row + ", " + col + "]"; 
    }


    //--------------------------------------------------------------------
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return col == location.col && row == location.row;
    }

    public int hashCode()
    {
        int result;
        result = row;
        result = 31 * result + col;
        return result;
    }



//    //--------------------------------------------------------------------
//    public static Location averageOf(Collection<Location> locations)
//    {
//        if (locations.isEmpty()) return ORIGIN;
//
//        int totalRows = 0;
//        int totalCols = 0;
//
//        for (Location loc : locations)
//        {
//            totalRows += loc.row;
//            totalCols += loc.col;
//        }
//
//        int avgRow = Math.round((float) totalCols / locations.size());
//        int avgCol = Math.round((float) totalRows / locations.size());
//        return new Location(avgRow, avgCol);
//    }
}
