package ru.ifmo.enf.optiks.physics.object.state;

import ru.ifmo.enf.optiks.physics.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public interface StateFactory {

    public State createActiveObjectState(final GameObject gameObject, final boolean isRotate);
}
