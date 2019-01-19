package ao.cps511.a2.tiles;

import javax.media.opengl.GL;
import javax.swing.*;

/**
 *
 */
public interface TileView<T>
        extends Prototype<TileView<T>>
{
    public JComponent icon();

    public void display(GL gl,
                        TileView above,
                        TileView below,
                        TileView leftOf,
                        TileView rightOf);
}
