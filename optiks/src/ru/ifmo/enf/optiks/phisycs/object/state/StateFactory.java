package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public interface StateFactory {
    public State createActiveObjectState(final GameObject gameObject, final boolean isRotate);
}
