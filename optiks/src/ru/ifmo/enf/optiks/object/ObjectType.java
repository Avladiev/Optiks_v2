package ru.ifmo.enf.optiks.object;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public enum ObjectType {
    LASER("LASER"),
    AIM("AIM"),
    BULLET("BULLET"),
    MIRROR_RECTANGLE("MIRROR_RECTANGLE"),
    MIRROR_CIRCLE("MIRROR_CIRCLE"),
    BARRIER_RECTANGLE("BARRIER_RECTANGLE"),
    BARRIER_CIRCLE("BARRIER_CIRCLE"),
    CIRCLE("CIRCLE"),
    RECTANGLE("RECTANGLE");


    public final String name;

    private ObjectType(final String name) {
        this.name = name;
    }

    public static ObjectType getType(final String type) {
        for (final ObjectType objectType : ObjectType.values()) {
            if (type.equalsIgnoreCase(objectType.name)) {
                return objectType;
            }
        }
        throw new IllegalArgumentException("strange type: " + type);
    }
}
