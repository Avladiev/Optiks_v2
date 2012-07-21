package ru.ifmo.enf.optiks.object.container;

import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.object.ObjectType;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class ObjectСontainer {

    private Vector2 pos;
    private float angle;
    private ObjectType objectType;

    public ObjectСontainer() {
    }

    public void setPos(final Vector2 pos) {
        this.pos = pos;
    }

    public void setAngle(final float angle) {
        this.angle = angle;
    }

    public void setObjectType(final ObjectType objectType) {
        this.objectType = objectType;
    }

    public ObjectСontainer(Vector2 pos, float angle, ObjectType objectType) {
        this.pos = pos;
        this.angle = angle;
        this.objectType = objectType;

    }

    public Vector2 getPos() {
        return pos;
    }

    public float getAngle() {
        return angle;
    }

    public ObjectType getObjectType() {
        return objectType;
    }
}
