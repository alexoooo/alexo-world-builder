package ao.cps511.a2.engine;

import ao.cps511.a2.base.OrthoRenderer;
import ao.cps511.a2.builder.Location;
import ao.cps511.a2.builder.Room;
import ao.cps511.a2.input.Architect;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

/**
 * Renders the running state of the game.
 */
public class BluePrint extends OrthoRenderer
{
    //--------------------------------------------------------------------
    //private final static int WIDTH  = 100;
    //private final static int HEIGHT = 100;


    //--------------------------------------------------------------------
    private Architect    controller;
    private Room         room;

    //private TextRenderer text;

    private Location     cursor;


    //--------------------------------------------------------------------
    public BluePrint(Architect control)
    {
        //super(WIDTH, HEIGHT);

        room       = new Room();
        controller = control;
        cursor     = Location.ORIGIN;

        //text = new TextRenderer(new Font("SansSerif", Font.BOLD, 18));
    }


    //--------------------------------------------------------------------
    protected void display(final GL gl, final GLU glu)
    {
        processInput();

        room.placeCursor( cursor );
        room.display( gl );

        if ( controller.cursorAtProjection() != null )
        {
            int viewport[] = new int[4];
            double mvmatrix[] = new double[16];
            double projmatrix[] = new double[16];
            int realy = 0;// GL y coord pos
            double wcoord[] = new double[4];// wx, wy, wz;// returned xyz coords

            int x = (int) controller.cursorAtProjection().getX(),
                y = (int) controller.cursorAtProjection().getY();
//            switch (mouse.getButton())
//            {
//                case MouseEvent.BUTTON1:
                    gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
                    gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix, 0);
                    gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projmatrix, 0);
                    /* note viewport[3] is height of window in pixels */
                    realy = viewport[3] - (int) y - 1;
//                    System.out.println("Coordinates at cursor are (" + x + ", " + realy + ")");
//                    glu.gluUnProject((double) x, (double) realy, 0.01, //
//                                     mvmatrix, 0,
//                                     projmatrix, 0,
//                                     viewport, 0,
//                                     wcoord, 0);
//                    System.out.println("World coords at z=0.0 are ( " //
//                                       + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
//                                       + ")");
                    glu.gluUnProject((double) x, (double) realy, -0.70, //
                                     mvmatrix, 0,
                                     projmatrix, 0,
                                     viewport, 0,
                                     wcoord, 0);
//                    System.out.println("World coords at z=1.0 are (" //
//                                       + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
//                                       + ")");
//                    break;
//                case MouseEvent.BUTTON2:
//                    break;
//                default:
//                    break;
//                }


//            Point2D cursorAtProjection =
//                    controller.cursorAtProjection();
//
//            int   viewport[] = new int[4];
//            double mvmatrix[] = new double[16];
//            double projmatrix[] = new double[16];
//
//            // GL y coord pos
//            double realy = viewport[3] - cursorAtProjection.getY() - 1;
//            double realx = cursorAtProjection.getX();
//
//            // wx, wy, wz;// returned xyz coords
//            double wcoord[] = new double[4];
//
//            gl.glGetIntegerv(GL.GL_VIEWPORT, viewport, 0);
//            gl.glGetDoublev(GL.GL_MODELVIEW_MATRIX, mvmatrix, 0);
//            gl.glGetDoublev(GL.GL_PROJECTION_MATRIX, projmatrix, 0);
//            /* note viewport[3] is height of window in pixels */
//
//            System.out.println("Coordinates at cursor at (" +
//                                    realx + ", " + realy + ")");
//            glu.gluUnProject(realx,      realy, 0.0,
//                             mvmatrix,   0,
//                             projmatrix, 0,
//                             viewport,   0,
//                             wcoord,     0);
            cursor = new Location((int) Math.round(wcoord[1] / 1),
                                  (int) Math.round(wcoord[0] / 1));

            controller.feedLocationEvent( cursor );

            if (Math.random() < 0.05)
            {
//                (double) x, (double) realy
                System.out.println("input location at : [" + x + ", " + y + "]");
                System.out.println("location at z: " + cursor);
                System.out.println("World coords at z=0.0 are ( " //
                                 + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
                                 + ")");
            }
//            glu.gluUnProject(realx, realy, 1.0, //
//              mvmatrix, 0,
//              projmatrix, 0,
//              viewport, 0,
//              wcoord, 0);
//            System.out.println("World coords at z=1.0 are (" //
//                             + wcoord[0] + ", " + wcoord[1] + ", " + wcoord[2]
//                             + ")");
        }


        //text.beginRendering(WIDTH * 4, HEIGHT * 4);
        //text.setColor(1.0f, 1.0f, 1.0f, 0.5f);
        //text.draw("hey, this is some text",
        //          0, (int)(HEIGHT * 3.75));
        //text.endRendering();
    }


    //--------------------------------------------------------------------
    private void processInput()
    {
        cursor = controller.nextLocation();


        if (controller.selectCurrentRequested())
        {
            controller.selectCurrent( room.tileAt(cursor).view() );
        }

        if (controller.applySelectedRequested())
        {
            room.add(cursor, controller.nextTile());
        }


    }
}

