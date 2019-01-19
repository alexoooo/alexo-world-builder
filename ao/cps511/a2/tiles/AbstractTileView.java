package ao.cps511.a2.tiles;

import javax.swing.*;

/**
 *
 */
public abstract class AbstractTileView
        implements TileView<AbstractTileView>
{
    //--------------------------------------------------------------------
    public JComponent icon()
    {
        return new JLabel( toString() );
    }


    //--------------------------------------------------------------------
    public TileView<AbstractTileView> prototype()
    {
        return this;
    }


    //--------------------------------------------------------------------
    public String toString()
    {
        return getClass().getSimpleName();
    }
}
