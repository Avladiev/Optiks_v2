package ru.ifmo.enf.optiks.object.container;

import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.object.ObjectType;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public interface GameObjectData {

    Vector2 getPosition();

    float getAngle();

    ObjectType getType();


}
