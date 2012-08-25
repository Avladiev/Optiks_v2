package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Mirror extends GameObject {

    public Mirror() {
        super(new Vector2(), new Vector2(), 1, 20);
        density = 1;
        friction = 1;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.continueShoot();
    }
}
