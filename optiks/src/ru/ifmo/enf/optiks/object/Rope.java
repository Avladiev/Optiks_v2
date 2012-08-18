package ru.ifmo.enf.optiks.object;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Rope extends GameObject {
    protected Rope() {
        super(new Vector2(1, 1), new Vector2(1, 1), 0.7f);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet) {
        getBody().getWorld().destroyBody(getBody());
        gravityOn();
        bullet.continueShoot();
    }
}
