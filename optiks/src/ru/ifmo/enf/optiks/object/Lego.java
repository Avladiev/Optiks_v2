package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Lego extends GameObject {
    protected Lego(final Vector2 anchorA, final Vector2 anchorB) {
        super(anchorA, anchorB, 1);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.stop();
    }
}
