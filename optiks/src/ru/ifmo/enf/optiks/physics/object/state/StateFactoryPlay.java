package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class StateFactoryPlay implements StateFactory {

    public State createActiveObjectState(final GameObject gameObject, final boolean isRotate) {

        if (!gameObject.hasNext() && !gameObject.hasPrevious()) {
            if (isRotate) {
                return new RotateWithoutNextState(gameObject);
            } else {
                return new DragSingleObjectState(gameObject);
            }
        }
        if (!gameObject.hasNext() && !isRotate) {
            return new DragWithoutNextState(gameObject);
        }
        return new DragState(gameObject);
    }
}
