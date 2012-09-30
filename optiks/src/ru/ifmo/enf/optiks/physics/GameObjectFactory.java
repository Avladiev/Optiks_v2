package ru.ifmo.enf.optiks.physics;

import ru.ifmo.enf.optiks.physics.object.*;
import ru.ifmo.enf.optiks.physics.object.container.SimpleObjectСontainer;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class GameObjectFactory {

    public static GameObject createGameObject(final SimpleObjectСontainer simpleObjectСontainer) {
        final GameObject object;
        switch (simpleObjectСontainer.getObjectType()) {
            case STATIC_CIRCLE_ATTACHER:
            case ATTACHER:
                object = new Attacher();
                break;
            case LEGO:
                object = new Lego();
                break;
            case LASER:
                object = new Laser();
                break;
            case AIM:
                object = new Aim();
                break;
            case MIRROR:
                object = new Mirror();
                break;
            case ROPE:
                object = new Rope();
                break;
            case ROCKET:
                object = new Rocket();
                break;
            case ATTACH_ZONE:
                object = new AttachZone();
                break;
            default:
                object = new Laser();
                break;
        }
        object.setOrigins(simpleObjectСontainer.getPos(), simpleObjectСontainer.getAngle());
        object.setObjectType(simpleObjectСontainer.getObjectType());

        return object;
    }
}
