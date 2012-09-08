package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class StateFactory {
    public static State createActiveObjectState(final GameObject gameObject, final boolean isRotate) {
        if (isRotate) {
            return new RotationState(gameObject);
        } else {
            return new DragState(gameObject);
        }


    }

    public static State createState(final GameObject gameObject) {
        return null;
    }


}
