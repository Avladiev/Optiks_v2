package ru.ifmo.enf.optiks.phisycs.object.state;

import ru.ifmo.enf.optiks.phisycs.object.GameObject;

/**
 * Author: Dudko Alex (dududko@gmail.com)
 */
public class DragSingleObjectState extends State {
    public DragSingleObjectState(final GameObject gameObject) {
        super(gameObject);
    }

    @Override
    protected void setPreProperties() {
        gameObject.getBody().setFixedRotation(true);
    }

    @Override
    protected void setPostProperties() {
        gameObject.getBody().setFixedRotation(false);
    }
}
