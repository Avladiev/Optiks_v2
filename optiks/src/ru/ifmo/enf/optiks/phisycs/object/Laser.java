package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Laser extends GameObject {

    public Laser() {
        super(new Vector2(0, 0), new Vector2(0, 0), 1, 20);
        density = 0;
        friction = 0;
        restitution = 0;

        setMovable(true);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.stop();
    }
}
