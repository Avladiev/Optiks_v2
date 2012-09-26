package ru.ifmo.enf.optiks.listeners.collision;

import com.badlogic.gdx.physics.box2d.Body;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 25.09.12
 */
public class CollisionResolver {
    public static void resolveCollision(final Body bodyA, final Body bodyB) {
        final GameObject objectA = (GameObject) bodyA.getUserData();
        final GameObject objectB = (GameObject) bodyB.getUserData();


    }
}
