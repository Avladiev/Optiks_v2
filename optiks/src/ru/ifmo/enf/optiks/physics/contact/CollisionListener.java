package ru.ifmo.enf.optiks.physics.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import ru.ifmo.enf.optiks.physics.object.Bullet;
import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class CollisionListener extends CollisionAdapter {

    @Override
    public void beginContact(final Contact contact) {
        final GameObject gameObjectA = (GameObject) contact.getFixtureA().getBody().getUserData();
        final GameObject gameObjectB = (GameObject) contact.getFixtureB().getBody().getUserData();

        if (gameObjectA instanceof Bullet) {
            gameObjectB.bulletHitReaction((Bullet) gameObjectA, contact.getFixtureB());
        } else if (gameObjectB instanceof Bullet) {
            gameObjectA.bulletHitReaction((Bullet) gameObjectB, contact.getFixtureA());
        }
    }
}
