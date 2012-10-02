package ru.ifmo.enf.optiks.physics.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Rope extends GameObject {

    public Rope() {
        super(new Vector2(0, 4), new Vector2(0, -4), 0.7f, 20, 60);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {
        gravityOn();

        getNext().setPrevious(null);
        getPrevious().setNext(null);

        getBody().getWorld().destroyBody(getBody());
        bullet.continueShoot();
    }
}
