package ru.ifmo.enf.optiks.listeners.collision;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import ru.ifmo.enf.optiks.util.Command;
import ru.ifmo.enf.optiks.util.CommandList;
import ru.ifmo.enf.optiks.physics.object.Attacher;
import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.Wall;

/**
 * Author: Sergey Fedorov (serezhka@xakep.ru)
 * Date: 25.09.12
 */
public class CollisionResolver {
    public static void resolveCollision(final Contact contact) {
        final GameObject objectA = (GameObject) contact.getFixtureA().getBody().getUserData();
        final GameObject objectB = (GameObject) contact.getFixtureB().getBody().getUserData();
        if (objectA instanceof Wall) {
            if (objectB instanceof Attacher) {
                /**
                 * index of wall side
                 * 0 - up
                 * 1 - left
                 * 2 - down
                 * 3 - right
                 */
                final int wallSide = objectA.getBody().getFixtureList().indexOf(contact.getFixtureA());
                final float angle = (float) (Math.PI / 2 * (wallSide + 2));

                CommandList.addCommand(new Command() {
                    @Override
                    public void doCommand() {
                        objectB.getBody().setType(BodyDef.BodyType.StaticBody);
                        objectB.getBody().setTransform(objectB.getBody().getWorldCenter(), angle);
                        objectB.getBody().setType(BodyDef.BodyType.DynamicBody);
                    }
                });
            }
        }
    }
}
