package ru.ifmo.enf.optiks.object;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public enum ObjectType {
    LASER("LASER"),
    AIM("AIM"),
    BULLET("BULLET"),
    MIRROR("MIRROR"),
    RECTANGLE_BARRIER("RECTANGLE_BARRIER"),
    STATIC_CIRCLE_ATTACHER("STATIC_CIRCLE_ATTACHER"),
    DYNAMIC_CIRCLE_ATTACHER("DYNAMIC_CIRCLE_ATTACHER"),
    STATIC_RECTANGLE_ATTACHER("STATIC_RECTANGLE_ATTACHER"),
    DYNAMIC_RECTANGLE_ATTACHER("DYNAMIC_RECTANGLE_ATTACHER"),
    STATIC_LEGO("STATIC_LEGO"),
    DYNAMIC_LEGO("DYNAMIC_LEGO"),
    PLAY("PLAY"),
    OPTIONS("OPTIONS"),
    ABOUT("ABOUT"),
    SEASON("SEASON"),
    LEVEL("LEVEL"),
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
