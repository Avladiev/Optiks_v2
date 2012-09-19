package ru.ifmo.enf.optiks.phisycs.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import ru.ifmo.enf.optiks.phisycs.object.Bullet;
import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class CollisionListener implements ContactListener{
    @Override
    public void beginContact(final Contact contact) {
        GameObject gameObjectA = (GameObject) contact.getFixtureA().getBody().getUserData();
        GameObject gameObjectB = (GameObject) contact.getFixtureB().getBody().getUserData();

        if (gameObjectA instanceof Bullet) {
            gameObjectB.bulletHitReaction((Bullet) gameObjectA, contact.getFixtureB());
        } else if (gameObjectB instanceof Bullet) {
            gameObjectA.bulletHitReaction((Bullet) gameObjectB, contact.getFixtureA());
        }
    }

    @Override
    public void endContact(final Contact contact) {
    }

    @Override
    public void preSolve(final Contact contact, final Manifold oldManifold) {
    }

    @Override
    public void postSolve(final Contact contact, final ContactImpulse impulse) {
    }
}
