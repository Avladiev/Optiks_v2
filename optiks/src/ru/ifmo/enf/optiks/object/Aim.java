package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Aim extends GameObject {

    public Aim() {
        super(new Vector2(5, 5), new Vector2(0, 0), 1);
        density = 1;
        friction = 1;
        restitution = 1;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.stop();
    }
}
