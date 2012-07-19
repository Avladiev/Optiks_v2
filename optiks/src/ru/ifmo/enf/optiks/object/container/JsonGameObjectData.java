package ru.ifmo.enf.optiks.object.container;

import com.badlogic.gdx.math.Vector2;
import ru.ifmo.enf.optiks.object.ObjectType;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class JsonGameObjectData implements GameObjectData {
    private final Vector2 position;
    private final float angine;
    private final ObjectType objectType;

    public JsonGameObjectData(final Vector2 position, final float angine, final ObjectType objectType) {
        this.position = position;
        this.angine = angine;
        this.objectType = objectType;
    }

    @Override
    public Vector2 getPosition() {
       return position;
    }

    @Override
    public float getAngle() {
       return angine;
    }

    @Override
    public ObjectType getType() {
        return objectType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final JsonGameObjectData that = (JsonGameObjectData) o;

        if (Float.compare(that.angine, angine) != 0) return false;
        if (objectType != that.objectType) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (angine != +0.0f ? Float.floatToIntBits(angine) : 0);
        result = 31 * result + (objectType != null ? objectType.hashCode() : 0);
        return result;
    }
}
