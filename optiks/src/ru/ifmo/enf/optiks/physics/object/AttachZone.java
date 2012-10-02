package ru.ifmo.enf.optiks.physics.object;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 26.09.12
 */
public class AttachZone extends GameObject {
    public AttachZone() {
        super(new Vector2(0, 0), new Vector2(0, 0), new Vector2(0, 0), 0, 20, 0);
    }

    @Override
    public void bulletHitReaction(final Bullet bullet, final Fixture fixtureA) {

    }
}
