package ao.cps511.a2.builder;

import ao.cps511.a2.engine.Visual;
import ao.cps511.a2.tiles.TilePanel;
import ao.cps511.a2.tiles.TileView;
import ao.cps511.a2.tiles.impl.Cursor;

import javax.media.opengl.GL;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Room implements Visual
{
    //--------------------------------------------------------------------
    private Map<Location, Tile> tiles;
    private Location            cursor;


    //--------------------------------------------------------------------
    public Room()
    {
        tiles = new HashMap<Location, Tile>();

        randomize();
//        tiles.put(new Location(0, 0), new Tile(new PlainFloor()));
//        tiles.put(new Location(1, 0), new Tile(new PlainFloor()));
//        tiles.put(new Location(0, 1), new Tile(new Box()));
//        tiles.put(new Location(1, 1), new Tile(new Box()));
    }

    private void randomize()
    {
        for (int row = -10; row <= 10; row++)
        {
            for (int col = -10; col <= 10; col++)
            {
                Location location = new Location(row, col);
                tiles.put(location, new Tile(TilePanel.randomTile()));
            }
        }
    }


    //--------------------------------------------------------------------
    public void add(Location location, TileView tile)
    {
        add(location, new Tile( tile ));
    }
    public void add(Location location, Tile tile)
    {
        tiles.put( location, tile );
    }

    public Tile tileAt(Location location)
    {
        return tiles.get( location );
    }


    //--------------------------------------------------------------------
    public void placeCursor(Location cursor)
    {
        this.cursor = cursor;
    }


    //--------------------------------------------------------------------
    public void display(GL gl)
    {
        for (Map.Entry<Location, Tile> tile : tiles.entrySet())
        {
            tile.getValue().displayAt( gl,  tile.getKey(), this );
        }

        new Tile(Cursor.INSTANCE).displayAt( gl, cursor, this );
    }


    //--------------------------------------------------------------------
//    private Location center()
//    {
//        return Location.averageOf( tiles.keySet() );
//    }
}
