package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
@Deprecated
public class RotationState extends State {
    public RotationState(final GameObject gameObject) {
        super(gameObject);
        if (gameObject.hasPrevious()) {
//            previous = new StaticState(gameObject.getPrevious());
        }
    }

    @Override
    protected void setPreProperties() {
        //todo

    }

    @Override
    protected void setPostProperties() {
        //todo

    }
}
