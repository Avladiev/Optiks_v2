package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class StateFactoryPlay implements StateFactory{
    public State createActiveObjectState(final GameObject gameObject, final boolean isRotate) {
        if (gameObject.hasNext()) {
            return new DragState(gameObject);
        } else {
            if (isRotate) {
                return new RotationState(gameObject);
            } else {
                return new DragWithoutNextState(gameObject);
            }
        }
    }
}
