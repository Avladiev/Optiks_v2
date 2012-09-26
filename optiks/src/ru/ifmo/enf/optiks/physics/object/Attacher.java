package ru.ifmo.enf.optiks.physics.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Attacher extends GameObject {

    public Attacher() {
        super(new Vector2(0, 0), new Vector2(0, 10), new Vector2(0, 0), 1, 20, 60);
        density = 1;
        friction = 1;
        restitution = 0;
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
        bullet.stop();
    }
}
