package ao.cps511.a2.run;

import ao.cps511.a2.engine.WorldBuilder;

/**
 * Entry point for the World Builder.
 */
public class BuilderStarter
{
    //--------------------------------------------------------------------
    private BuilderStarter() {}


    //--------------------------------------------------------------------
    public static void main(String[] args)
    {
        new WorldBuilder().start();
    }
}
