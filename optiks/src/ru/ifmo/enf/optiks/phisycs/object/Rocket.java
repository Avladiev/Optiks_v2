package ru.ifmo.enf.optiks.phisycs.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Rocket extends GameObject {
    public Rocket() {
        super(new Vector2(0, 0), new Vector2(0, 0), 0.5f, 20);
        density = 1;
        friction = 1;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        // todo boom
        bullet.stop();
    }
}
