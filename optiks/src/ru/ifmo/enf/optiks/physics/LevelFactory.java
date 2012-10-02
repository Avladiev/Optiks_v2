package ru.ifmo.enf.optiks.physics;

import ru.ifmo.enf.optiks.physics.object.GameObject;
import ru.ifmo.enf.optiks.physics.object.container.LevelContainer;
import ru.ifmo.enf.optiks.physics.object.container.ObjectContainer;
import ru.ifmo.enf.optiks.physics.object.container.SimpleObjectСontainer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class LevelFactory {

    public static List<GameObject> createLevel(final LevelContainer levelContainer) {
        final List<GameObject> gameObjects = new ArrayList<GameObject>();
        for (final ObjectContainer objectContainer : levelContainer.getObjectContainers()) {
            gameObjects.add(createMultiplexGameObject(objectContainer));
        }
        for (final SimpleObjectСontainer simpleObjectСontainer : levelContainer.getSimpleObjectСontainers()) {
            gameObjects.add(createSimpleGameObject(simpleObjectСontainer));
        }
        return gameObjects;
    }

    private static GameObject createMultiplexGameObject(final ObjectContainer objectContainer) {
        final Iterator<SimpleObjectСontainer> iterator = objectContainer.getAll().iterator();
        final GameObject attacher = createSimpleGameObject(iterator.next());
        GameObject current = attacher;
        GameObject next;
        while (iterator.hasNext()) {
            next = createSimpleGameObject(iterator.next());
            current.setNext(next);
            next.setPrevious(current);
            // todo collide connection

//            next.setJoint(world.createJoint(RevoluteJointBehavior.createRevoluteJoint(next, current, collide)));

            /*final JointDef jointDef = JointDefFactory.createJointDef(current, next);
            next.setJoint(world.createJoint(jointDef));*/

//            RevoluteJoint joint = next.getJoint();
//            joint.setLimits(joint.getJointAngle(), joint.getJointAngle());

            current = next;
        }
        return attacher;
    }

    private static GameObject createSimpleGameObject(final SimpleObjectСontainer simpleObjectСontainer) {
        return GameObjectFactory.createGameObject(simpleObjectСontainer);
    }
}
