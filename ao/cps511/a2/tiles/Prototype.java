package ao.cps511.a2.tiles;

/**
 *
 */
public interface Prototype<T extends Prototype<T>>
{
    public T prototype();
}
