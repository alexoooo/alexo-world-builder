package ao.cps511.a2.tiles.impl;

import ao.cps511.a2.base.Painter;
import ao.cps511.a2.builder.Tile;
import ao.cps511.a2.tiles.TileView;

import javax.media.opengl.GL;
import java.awt.*;

/**
 *
 */
public class Box extends PlainFloor
{
    //--------------------------------------------------------------------
    private static final double quarterSize = Tile.UNIT_SIZE / 4;


    //--------------------------------------------------------------------
    public void display(GL       gl,
                        TileView above,
                        TileView below,
                        TileView leftOf,
                        TileView rightOf)
    {
        super.display(gl, above, below, leftOf, rightOf);

        gl.glBegin(GL.GL_QUADS);
        gl.glTranslated( quarterSize,  quarterSize, 0);

        Painter.apply(gl, Color.BLACK, 0.8);
        gl.glVertex2d(-quarterSize,  quarterSize);
        gl.glVertex2d( quarterSize,  quarterSize);
        gl.glVertex2d( quarterSize, -quarterSize);
        gl.glVertex2d(-quarterSize, -quarterSize);

        gl.glTranslated(-quarterSize, -quarterSize, 0);
        gl.glEnd();
    }
}
