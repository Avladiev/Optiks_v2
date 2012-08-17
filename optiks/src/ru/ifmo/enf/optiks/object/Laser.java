package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Laser extends GameObject {

    public Laser() {
        super(new Vector2(1, 1), new Vector2(1, 1));
        density = 0;
        friction = 0;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Body bullet) {
        //todo

    }
}
