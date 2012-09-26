package ru.ifmo.enf.optiks.listeners.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import ru.ifmo.enf.optiks.physics.contact.CollisionAdapter;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 25.09.12
 */
public class EditCollisionListener extends CollisionAdapter {

    @Override
    public void beginContact(final Contact contact) {
        CollisionResolver.resolveCollision(contact);
    }
}
