package ao.cps511.a2.tiles.impl;

import ao.cps511.a2.base.Painter;
import ao.cps511.a2.builder.Tile;
import ao.cps511.a2.tiles.AbstractTileView;
import ao.cps511.a2.tiles.TileView;
import ao.cps511.a2.tiles.Walled;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public class Wall
        extends AbstractTileView
        implements Walled
{
    //--------------------------------------------------------------------
    private static final double half      = Tile.UNIT_SIZE / 2;
    private static final double width     = Tile.UNIT_SIZE / 10;
    private static final double halfWidth = width / 2;


    //--------------------------------------------------------------------
    public void display(GL       gl,
                        TileView above,
                        TileView below,
                        TileView leftOf,
                        TileView rightOf)
    {
        boolean connectsAbove =
                (above != null && above instanceof Walled);
        boolean connectsBelow =
                (below != null && below instanceof Walled);
        boolean connectsOnLeft =
                (leftOf != null && leftOf instanceof Walled);
        boolean connectsOnRight =
                (rightOf != null && rightOf instanceof Walled);

        display(gl, connectsAbove,
                    connectsBelow,
                    connectsOnLeft,
                    connectsOnRight);
    }


    //--------------------------------------------------------------------
    private void display(GL gl,
                         boolean connectsAbove,
                         boolean connectsBelow,
                         boolean connectsOnLeft,
                         boolean connectsOnRight)
    {
        boolean connectsNone = !connectsAbove  &&
                               !connectsBelow  &&
                               !connectsOnLeft &&
                               !connectsOnRight;

        if (connectsNone || connectsAbove)
        {
            displayQuarterWall(gl);
        }
        if (connectsNone || connectsBelow)
        {
            gl.glRotated( 180, 0, 0, 1);
            displayQuarterWall(gl);
            gl.glRotated(-180, 0, 0, 1);
        }
        if (connectsOnLeft)
        {
            gl.glRotated( 90, 0, 0, 1);
            displayQuarterWall(gl);
            gl.glRotated(-90, 0, 0, 1);
        }
        if (connectsOnRight)
        {
            gl.glRotated( 270, 0, 0, 1);
            displayQuarterWall(gl);
            gl.glRotated(-270, 0, 0, 1);
        }
    }


    //--------------------------------------------------------------------
    private void displayQuarterWall(GL gl)
    {
        gl.glBegin(GL.GL_QUADS);

        Color c = Color.DARK_GRAY;
        Painter.apply(gl, c, 0.8);

        // bottom
        gl.glVertex3d(-halfWidth, half, 0);
        gl.glVertex3d( halfWidth, half, 0);
        gl.glVertex3d( halfWidth, 0,    0);
        gl.glVertex3d(-halfWidth, 0,    0);

        // top
        gl.glVertex3d(-halfWidth, half, 10);
        gl.glVertex3d( halfWidth, half, 10);
        gl.glVertex3d( halfWidth, 0,    10);
        gl.glVertex3d(-halfWidth, 0,    10);

        // front
        gl.glVertex3d(-halfWidth, half, 10);
        gl.glVertex3d( halfWidth, half, 10);
        gl.glVertex3d( halfWidth, half,  0);
        gl.glVertex3d(-halfWidth, half,  0);

        // back
        gl.glVertex3d( halfWidth, 0, 10);
        gl.glVertex3d(-halfWidth, 0, 10);
        gl.glVertex3d( halfWidth, 0,  0);
        gl.glVertex3d(-halfWidth, 0,  0);

        Painter.apply(gl, c.darker(), 0.8);

        // left
        gl.glVertex3d(-halfWidth, half, 10);
        gl.glVertex3d(-halfWidth, 0,    10);
        gl.glVertex3d(-halfWidth, 0,     0);
        gl.glVertex3d(-halfWidth, half,  0);

        // right
        gl.glVertex3d(halfWidth, half,  0);
        gl.glVertex3d(halfWidth, 0,     0);
        gl.glVertex3d(halfWidth, 0,    10);
        gl.glVertex3d(halfWidth, half, 10);

        gl.glEnd();
    }
}
