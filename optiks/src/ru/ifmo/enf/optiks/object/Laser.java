package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Laser extends GameObject {

    public Laser() {
        super(new Vector2(1, 1), new Vector2(1, 1), 1);
        density = 0;
        friction = 0;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.stop();
    }
}
