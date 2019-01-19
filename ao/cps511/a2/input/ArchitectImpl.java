package ao.cps511.a2.input;

import ao.cps511.a2.builder.Location;
import ao.cps511.a2.tiles.TilePanel;
import ao.cps511.a2.tiles.TileView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

/**
 *
 */
public class ArchitectImpl
        implements Architect,
                   KeyListener,
                   MouseListener,
                   MouseMotionListener
{
    //--------------------------------------------------------------------
    private volatile Direction pendingDir        = null;
    private volatile boolean   applyTilePending  = false;
    private volatile boolean   selectTilePending = false;
    private volatile Location  nextLocation      = Location.ORIGIN;

//    private JFrame    tileFrame;
    private TilePanel tilePanel;

    private Point mousePosition;


    //--------------------------------------------------------------------
    public ArchitectImpl(Component listenTo)
    {
        listenTo.addKeyListener(         this );
        listenTo.addMouseListener(       this );
        listenTo.addMouseMotionListener( this );

//        tileFrame = new JFrame("Tile Selection");
//        tileFrame = new Window(null);
//        tileFrame.setDefaultCloseOperation(
//                WindowConstants.EXIT_ON_CLOSE);

        tilePanel = new TilePanel();

//        tileFrame.add( tilePanel );
//        tileFrame.pack();
//        tileFrame.setVisible( true );
    }


    //--------------------------------------------------------------------
    public JComponent tileSelection()
    {
        return tilePanel;
    }

    //--------------------------------------------------------------------
//    public Direction nextDirection()
//    {
//        Direction ret = pendingDir;
//        pendingDir = null;
//        return ret;
//    }

    //public Direction nextDirection();
    public Location nextLocation()
    {
        return nextLocation;
    }

    public TileView nextTile()
    {
//        TileView ret = null;
//        if (pendingTile != null)
//        {
//            ret = pendingTile;
//            pendingTile = null;
//        }
//        else
//        {
//            ret = tilePanel.selectedTile();
//        }
//
//        return ret;
        return tilePanel.selectedTile();
    }

    public boolean selectCurrentRequested()
    {
        boolean ret = selectTilePending;
        selectTilePending = false;
        return ret;
    }

    public boolean applySelectedRequested()
    {
        boolean retApply = applyTilePending;
        applyTilePending = false;
        return retApply;
    }

    public void selectCurrent(TileView current)
    {
        tilePanel.select( current );
    }

    public Point2D cursorAtProjection()
    {
        return mousePosition;
    }


    //--------------------------------------------------------------------
    public void feedDirectionEvent(Direction dir)
    {
        pendingDir = dir;
        updateNextLocation();
    }
    public void feedLocationEvent(Location loc)
    {
        nextLocation = loc;
    }

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                feedDirectionEvent( Direction.WEST );
                break;

            case KeyEvent.VK_RIGHT:
                feedDirectionEvent( Direction.EAST );
                break;

            case KeyEvent.VK_UP:
                feedDirectionEvent( Direction.NORTH );
                break;

            case KeyEvent.VK_DOWN:
                feedDirectionEvent( Direction.SOUTH );
                break;

            case KeyEvent.VK_SPACE:
                applyTilePending = true;
                break;

            case KeyEvent.VK_ENTER:
                selectTilePending = true;
                break;
        }
    }
    public void keyTyped(KeyEvent e)    {}
    public void keyReleased(KeyEvent e) {}

    private void updateNextLocation()
    {
        if (pendingDir != null)
        {
            nextLocation = pendingDir.offset( nextLocation );
        }
    }


    //--------------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            applyTilePending = true;
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            selectTilePending = true;
        }
    }

    public void mousePressed(MouseEvent e)
    {
//        applyTilePending = true;
    }
    public void mouseReleased(MouseEvent e) {}


    //--------------------------------------------------------------------
    public void mouseEntered(MouseEvent e)
    {
        mousePosition = e.getPoint();
    }
    public void mouseExited(MouseEvent e)
    {
        mousePosition = null;
    }

    public void mouseDragged(MouseEvent e)
    {
        applyTilePending = true;
        mouseMoved( e );
    }
    public void mouseMoved(MouseEvent e)
    {
        mousePosition = e.getPoint();
    }


    //--------------------------------------------------------------------
//    protected void finalize() throws Throwable
//    {
//        try
//        {
//            tileFrame.dispose();
//        }
//        finally
//        {
//            super.finalize();
//        }
//    }
}
