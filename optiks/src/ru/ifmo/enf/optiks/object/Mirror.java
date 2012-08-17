package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Mirror extends GameObject {

    protected Mirror(final Vector2 anchorA, final Vector2 anchorB) {
        super(anchorA, anchorB);
    }

    @Override
    public void bulletHitReaction(final Body bullet) {
        //todo

    }
}
