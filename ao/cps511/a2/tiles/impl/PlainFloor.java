package ao.cps511.a2.tiles.impl;

import ao.cps511.a2.base.Painter;
import ao.cps511.a2.builder.Tile;
import ao.cps511.a2.tiles.AbstractTileView;
import ao.cps511.a2.tiles.TileView;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public class PlainFloor extends AbstractTileView
{
    //--------------------------------------------------------------------
    private static final double halfSize = Tile.UNIT_SIZE / 2;


    //--------------------------------------------------------------------
    public void display(GL       gl,
                        TileView above,
                        TileView below,
                        TileView leftOf,
                        TileView rightOf)
    {
        gl.glBegin(GL.GL_QUADS);

        Painter.apply(gl, Color.GRAY, 0.8);
        gl.glVertex2d(-halfSize,  halfSize);
        gl.glVertex2d( halfSize,  halfSize);
        gl.glVertex2d( halfSize, -halfSize);
        gl.glVertex2d(-halfSize, -halfSize);

        gl.glEnd();
    }
}
