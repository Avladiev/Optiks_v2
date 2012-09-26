package ru.ifmo.enf.optiks.listeners.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 25.09.12
 */
public class EditCollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        CollisionResolver.resolveCollision(contact.getFixtureA().getBody(), contact.getFixtureB().getBody());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
