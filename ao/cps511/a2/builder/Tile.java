package ao.cps511.a2.builder;

import ao.cps511.a2.base.Translator;
import ao.cps511.a2.input.Direction;
import ao.cps511.a2.tiles.TileView;

import javax.media.opengl.GL;

/**
 *
 */
public class Tile
{
    //--------------------------------------------------------------------
    public static final double UNIT_SIZE = 10;


    //--------------------------------------------------------------------
    private final TileView view;


    //--------------------------------------------------------------------
    public Tile(TileView display)
    {
        view = display;
    }


    //--------------------------------------------------------------------
    public TileView view()
    {
        return view;
    }


    //--------------------------------------------------------------------
    public void displayAt(
            final GL       gl,
            final Location location,
            final Room     in)
    {
        final Tile above   = in.tileAt(Direction.NORTH.offset(location));
        final Tile below   = in.tileAt(Direction.SOUTH.offset(location));
        final Tile leftOf  = in.tileAt(Direction.WEST.offset(location));
        final Tile rightOf = in.tileAt(Direction.EAST.offset(location));

        double deltaX = location.column() * UNIT_SIZE;
        double deltaY = location.row()    * UNIT_SIZE;

        Translator t = new Translator(gl);
        t.translated(deltaX, deltaY, new Runnable() {
            public void run() {
                view.display(gl,
                             (above   == null ? null : above.view),
                             (below   == null ? null : below.view),
                             (leftOf  == null ? null : leftOf.view),
                             (rightOf == null ? null : rightOf.view));
            }
        });
    }
}
