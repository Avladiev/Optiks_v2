package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Wall extends GameObject {

    public Wall() {
        super(new Vector2(), new Vector2(), 0, 0);
        density = 0;
        friction = 1;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        bullet.stop();
    }
}
