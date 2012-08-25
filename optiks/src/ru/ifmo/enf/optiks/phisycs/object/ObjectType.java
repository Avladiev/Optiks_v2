package ru.ifmo.enf.optiks.phisycs.object;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public enum ObjectType {

    LASER("LASER"),
    AIM("AIM"),
    BULLET("BULLET"),
    MIRROR("MIRROR"),
    RECTANGLE_BARRIER("RECTANGLE_BARRIER"),
    STATIC_CIRCLE_ATTACHER("CIRCLE_ATTACHER"),
    ATTACHER("ATTACHER"),
    LEGO("LEGO"),
    DYNAMIC_LEGO("DYNAMIC_LEGO"),
    CIRCLE("CIRCLE"),
    RECTANGLE("RECTANGLE"),
    ROPE("ROPE"),
    ROCKET("ROCKET");

    private final String name;

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

    @Override
    public String toString() {
        return name;
    }
}
