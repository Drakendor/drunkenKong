package org.academiadecodigo.bootcamp11.drunkenkong;

/**
 * Created by codecadet on 09/10/17.
 */
public interface Collidable extends Drawable {

    boolean comparePosition(Collidable object);
}