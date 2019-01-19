package ao.cps511.a2.tiles;

import ao.cps511.a2.tiles.impl.Box;
import ao.cps511.a2.tiles.impl.PlainFloor;
import ao.cps511.a2.tiles.impl.Wall;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TilePanel extends JPanel
{
    //--------------------------------------------------------------------
    private static final List<TileView> tileChoices =
            new ArrayList<TileView>() {{
                add(new PlainFloor());
                add(new Box());
                add(new Wall());
            }};

    public static TileView randomTile()
    {
        return tileChoices.get(
                    (int)( Math.random() * tileChoices.size() ));
    }


    //--------------------------------------------------------------------
    private TileView selected      = tileChoices.get(0);
    private JPanel   selectedPanel = new JPanel();


    //--------------------------------------------------------------------
    public TilePanel()
    {
        selectedPanel.add( selected.icon() );
        add(new JLabel("Selected Tile Is:"));
        add( selectedPanel );

        for (final TileView tile : tileChoices)
        {
            JPanel subPane = new JPanel();
            subPane.add( tile.icon() );

            subPane.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    updateSelection( tile );
                }
            });

            add( subPane );
        }
    }

    private void updateSelection(TileView tile)
    {
        selected = tile;
        selectedPanel.removeAll();
        selectedPanel.add(selected.icon());
        revalidate();
    }


    //--------------------------------------------------------------------
    public TileView selectedTile()
    {
        return selected;
    }

    public void select(TileView tile)
    {
        updateSelection( tile );
    }
}
