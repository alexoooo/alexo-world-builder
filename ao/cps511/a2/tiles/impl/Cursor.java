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
public class Cursor extends AbstractTileView
{
    //--------------------------------------------------------------------
    public static final Cursor INSTANCE = new Cursor();


    //--------------------------------------------------------------------
    private static final double size   = Tile.UNIT_SIZE * 0.10;
    private static final double border = Tile.UNIT_SIZE * 0.33;


    //--------------------------------------------------------------------
    public void display(GL       gl,
                        TileView above,
                        TileView below,
                        TileView leftOf,
                        TileView rightOf)
    {
        gl.glBegin(GL.GL_QUADS);
        gl.glTranslated(border, border, 0);

        Painter.apply(gl, Color.RED, 0.5);
        gl.glVertex3d(-size,  size, 2);
        gl.glVertex3d( size,  size, 2);
        gl.glVertex3d( size, -size, 2);
        gl.glVertex3d(-size, -size, 2);

        gl.glTranslated(-border, -border, 0);
        gl.glEnd();
    }
}
