package ru.ifmo.enf.optiks.physics.object.container;

import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.physics.object.ObjectType;

/**
 * Author: Aleksey Vladiev (Avladiev2@gmail.com)
 */
public class SimpleObjectСontainer {

    private Vector2 pos;
    private float angle;
    private ObjectType objectType;

    public SimpleObjectСontainer() {
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

    public SimpleObjectСontainer(final Vector2 pos, final float angle, final ObjectType objectType) {
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SimpleObjectСontainer that = (SimpleObjectСontainer) o;

        if (Float.compare(that.angle, angle) != 0) return false;
        if (objectType != that.objectType) return false;
        if (pos != null ? !pos.equals(that.pos) : that.pos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pos != null ? pos.hashCode() : 0;
        result = 31 * result + (angle != +0.0f ? Float.floatToIntBits(angle) : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        return result;
    }
}
