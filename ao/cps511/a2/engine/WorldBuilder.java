package ao.cps511.a2.engine;

import ao.cps511.a2.input.Architect;
import ao.cps511.a2.input.ArchitectImpl;
import com.sun.opengl.util.FPSAnimator;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 */
public class WorldBuilder
{
    //--------------------------------------------------------------------
    private static final int DEFAULT_WIDTH  = 640;
    private static final int DEFAULT_HEIGHT = 480;


    //--------------------------------------------------------------------
    private JFrame      frame;
    private GLCanvas    glCanvas;
    private FPSAnimator animator;

    
    //--------------------------------------------------------------------
    public WorldBuilder(
            int displayWidth,
            int displayHeight)
    {
        glCanvas = new GLCanvas(new GLCapabilities());
        glCanvas.setSize(displayWidth, displayHeight);
        //glCanvas.setIgnoreRepaint(true);

        Architect architect    = new ArchitectImpl(glCanvas);
        BluePrint roomRenderer = new BluePrint( architect );
        glCanvas.addGLEventListener(roomRenderer);

        frame = new JFrame("World Build by Alex Ostrovsky and Leo Kwok");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(
                architect.tileSelection(), BorderLayout.NORTH);
        frame.getContentPane().add(glCanvas, BorderLayout.CENTER);

        animator = new FPSAnimator(glCanvas, 30);
        animator.setRunAsFastAsPossible(false);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
    }

    public WorldBuilder()
    {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }


    //--------------------------------------------------------------------
    public void start()
    {
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setLocation(
                (screenSize.width - frame.getWidth()) / 2,
                (screenSize.height - frame.getHeight()) / 2);
        frame.setVisible(true);

        glCanvas.requestFocus();

        animator.start();
    }

    
    //--------------------------------------------------------------------
    public void stop()
    {
        animator.stop();
        frame.dispose();
    }


//    //--------------------------------------------------------------------
//    public void finalize() throws Throwable
//    {
//        animator.stop();
//        super.finalize();
//    }
}
