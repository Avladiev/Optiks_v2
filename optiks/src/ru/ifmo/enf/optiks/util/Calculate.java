package ru.ifmo.enf.optiks.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class Calculate {

    public static double calculateDistance(final Vector2 point, final float x2, final float y2) {
        return Math.sqrt(Math.pow((point.x - x2), 2) + Math.pow((point.y - y2), 2));
    }

    public static double calculateDistance(final Vector2 pointA, final Vector2 pointB) {
        return calculateDistance(pointA, pointB.x, pointB.y);
    }
}
