package ao.cps511.a2.input;

import ao.cps511.a2.builder.Location;
import ao.cps511.a2.tiles.TileView;

import javax.swing.*;
import java.awt.geom.Point2D;

/**
 *
 */
public interface Architect
{
    //public Direction nextDirection();
    public Location nextLocation();

    public TileView nextTile();

    public boolean selectCurrentRequested();

    public boolean applySelectedRequested();

    public void selectCurrent(TileView current);

    public Point2D cursorAtProjection();

    public void feedLocationEvent(Location loc);
    public void feedDirectionEvent(Direction dir);

    public JComponent tileSelection();

//    public boolean shotPending();

//    public double percentX();

//    public boolean isPaused();
}
