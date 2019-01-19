package ao.cps511.a2.base;

import ao.cps511.a2.engine.Visual;

import javax.media.opengl.GL;

/**
 *
 */
public class Translator implements Visual
{
    //--------------------------------------------------------------------
    private GL     gl;

    private Visual deleget;
    private double translateX;
    private double translateY;



    //--------------------------------------------------------------------
    public Translator(GL gl)
    {
        this.gl = gl;
    }

    public Translator(double x,
                      double y,
                      Visual displayDeleget)
    {
        deleget    = displayDeleget;
        translateX = x;
        translateY = y;
    }

    //--------------------------------------------------------------------
    public void display(GL gl)
    {
        assert deleget != null;

        gl.glTranslated( translateX,  translateY, 0);
        deleget.display( gl );
        gl.glTranslated(-translateX, -translateY, 0);
    }


    //--------------------------------------------------------------------
    public void translated(double x, double y, Runnable r)
    {
        assert gl != null;

        gl.glTranslated( x,  y, 0);
        r.run();
        gl.glTranslated(-x, -y, 0);
    }
}
